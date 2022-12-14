package com.ssafy.mas.service;

import com.ssafy.mas.database.entity.Member;
import com.ssafy.mas.database.repository.MemberRepository;
import com.ssafy.mas.database.repository.UpdateLogRepository;
import com.ssafy.mas.util.ExecuteScript;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


@Service("fileUploadService")
@RequiredArgsConstructor
@Transactional
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    UpdateLogRepository updateLogRepository;

    @Autowired
    ExecuteScript executeScript;

    private String RESULT_PATH;

    @Override
    public boolean saveFile(String userid, MultipartFile[] files) {
        String osName = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        String homePath = System.getProperty("user.home");
        String fileSeperator = File.separator; // Windows: \\, linux: /

        String storagePath = null;
        if("windows 10".equals(osName)) {
            storagePath = homePath + fileSeperator + "Desktop" + fileSeperator + "storage";
        } else if("linux".equals(osName)) {
            storagePath = homePath + fileSeperator + "upload";
        } else {
            System.out.println("This OS is not supported.");
            return false;
        }
        File storage = new File(storagePath);
        LocalDateTime currentTime = LocalDateTime.now();
        String curTimeString = currentTime.toString().replaceAll("[^0-9]+", "");

        if(!storage.exists()) storage.mkdirs();

        String savePath = storagePath + fileSeperator + userid + fileSeperator + curTimeString;
        storage = new File(savePath);
        if(!storage.exists()) storage.mkdirs();

        for (int i = 0; i < files.length; i++) {
            String originalFileName = files[i].getOriginalFilename();
            String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            String fileType = null;

            if("zip".equals(ext)) {
                fileType = "kakaotalk";
            } else if("txt".equals(ext)) {
                fileType = "sms";
            } else {
                System.out.println("WRONG FILE EXT");
                return false;
            }

            String newFileName = fileType + "." + ext;
            File changeFile = new File(savePath + fileSeperator + newFileName);

            // File upload
            try {
                files[i].transferTo(changeFile);
                System.out.printf("[%s] - File upload is complete. The file is in %s\n", newFileName, savePath);

            } catch (IllegalStateException | IOException e) {
                System.out.printf("[%s] - File upload failed.\n", newFileName);
                e.printStackTrace();
                return false;
            }
        }
        Member findMember = memberRepository.findFirstByUserIdAndWithdrawal(userid, false);
        updateLogRepository.updateProfileUploadLog(findMember, currentTime.toLocalDate());

        // ?????? ????????? ??????????????? airflow ?????? ??????
        // AIRFLOW TRIGGER //

        return true;
    }

    @Override
    public JSONObject runPipeline(String userid, String dateString) {
        String host_dir = "/root/upload/"+ userid + "/" + dateString;
        // ????????? ?????? ?????? ?????? ?????????
        String result_path = "/home/j7b103/word";
        HashMap<String, ArrayList<Object>> output = executeScript.run_shell(
                host_dir,
                result_path);

        JSONObject jsonObject = new JSONObject();
        // ????????? ?????? ??????
        if(output != null){
            jsonObject.put("response", output);
        }else{
            jsonObject.put("response", "failed");
        }
        return jsonObject;
    }
}
