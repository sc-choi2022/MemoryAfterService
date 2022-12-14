# Android fundamentals 01.2 Part A: Your first interactive UI



## 1. Welcome

### Introduction

The user interface (UI) that appears on a screen of an Android device consists of a hierarchy of objects called *views* — every element of the screen is a View. The `View` class represents the basic building block for all UI components, and the base class for classes that provide interactive UI components such as buttons, checkboxes, and text entry fields. Commonly used `View` subclasses described over several lessons include:

- `TextView` for displaying text.
- `EditText` to enable the user to enter and edit text.
- `Button`and other clickable elements (such as `RadioButton`, and `Spinner`) to provide interactive behavior.
- `ScrollView` and `RecyclerView`to display scrollable items.
- `ImageView` for displaying images.
- `ConstraintLayout` and `LinearLayout` for containing other `View` elements and positioning them.

The Java code that displays and drives the UI is contained in a class that extends `Activity`. An `Activity` is usually associated with a layout of UI views defined as an XML (eXtended Markup Language) file. This XML file is usually named after its `Activity` and defines the layout of `View` elements on the screen.

For example, the `MainActivity` code in the Hello World app displays a layout defined in the `activity_main.xml` layout file, which includes a `TextView` with the text "Hello World".

In more complex apps, an `Activity` might implement actions to respond to user taps, draw graphical content, or request data from a database or the internet. 

### What you should already know

You should be familiar with:

- How to install and open Android Studio.
- How to create the HelloWorld app.
- How to run the HelloWorld app.



### What you'll learn

- How to create an app with interactive behavior.
- How to use the layout editor to design a layout.
- How to edit the layout in XML.
- A lot of new terminology. 



### What you'll do

- Create an app and add two `Button` elements and a `TextView` to the layout.
- Manipulate each element in the `ConstraintLayout` to constrain them to the margins and other elements.
- Change UI element attributes.
- Edit the app's layout in XML.
- Extract hardcoded strings into string resources.
- Implement click-handler methods to display messages on the screen when the user taps each `Button`.



## 2. App overview

The HelloToast app consists of two `Button` elements and one `TextView`. 

When the user taps the first `Button`, it displays a short message (a [`Toast`) on the screen. 

Tapping the second `Button` increases a "click" counter displayed in the `TextView`, which starts at zero.



## 3. Task 1: Create and explore a new project

### 1. Create the Android Studio project

![image-20220907172513183](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.2 Part A Your first interactive UI.assets\image-20220907172513183.png)

2. Select **Run > Run app** or click the **Run icon** ![Run Icon](https://developer.android.com/static/codelabs/android-training-layout-editor-part-a/img/6c65750f2ce7f651.png?hl=ko) in the toolbar to build and execute the app on the emulator or your device.



### 2. Explore the layout editor

Android Studio provides the layout editor for quickly building an app's layout of user interface (UI) elements.

It lets you drag elements to a visual design and blueprint view, position them in the layout, add constraints, and set attributes. 

*Constraints* determine the position of a UI element within the layout. A constraint represents a connection or alignment to another view, the parent layout, or an invisible guideline.

![image-20220907173637177](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.2 Part A Your first interactive UI.assets\image-20220907173637177.png)

2️⃣ You use the **Design** tab to manipulate elements and the layout, and the **Code** tab to edit the XML code for the layout. The **Split** tab allows you to edit the code and view the layout at the same time.

3️⃣ The **Palette** pane shows UI elements that you can use in your app's layout.

4️⃣ The **Component Tree** pane shows the view hierarchy of UI elements. 

6️⃣ The **Attributes** tab displays the **Attributes** pane for setting properties for a UI element.



## 4. Task 2: Add View elements in the layout editor

In this task you create the UI layout for the HelloToast app in the layout editor using the `ConstraintLayout` features. You can create the constraints manually, as shown later, or automatically using the **Autoconnect** tool.



### 1. Examine the element constraints

1. Open `activity_main.xml` 

​	If there is no blueprint, click the **Select Design Surface** button ![click the Select Design Surface button [ICON HERE] in the toolbar and choose Design + Blueprint. [IMAGEINFO]: ic_blueprint_icon.png, Blueprint button](https://developer.android.com/static/codelabs/android-training-layout-editor-part-a/img/51cea7d6d81ab452.png?hl=ko) in the toolbar and choose **Design and Blueprint**.

2. The **Autoconnect** tool ![ensure that the tool is not disabled. [IMAGEINFO]: ic_autoconnect_enabled_icon.png, Autoconnect button](https://developer.android.com/static/codelabs/android-training-layout-editor-part-a/img/dd3846009e393c48.png?hl=ko) is also located in the toolbar. It is enabled by default. For this step, ensure that the tool is not disabled.

4. Select **TextView** in the **Component Tree** pane. The "Hello World" `TextView` is highlighted in the design and blueprint panes and the constraints for the element are visible.

5. Refer to the animated figure below for this step. Control+click the circular handle on the right side of the `TextView` to delete the horizontal constraint that binds the view to the right side of the layout. The `TextView` jumps to the left side because it is no longer constrained to the right side. To add back the horizontal constraint, click the same handle and drag a line to the right side of the layout.

![Deleting and adding a constraint](https://developer.android.com/static/codelabs/android-training-layout-editor-part-a/img/7ea557d8751d03e4.gif?hl=ko)

* Constraint handle!![image-20220907231546564](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.2 Part A Your first interactive UI.assets\image-20220907231546564.png)
* Resizing handle ![image-20220907231529780](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.2 Part A Your first interactive UI.assets\image-20220907231529780.png)



### 2. Add a Button to the layout

When enabled, the **Autoconnect** tool automatically creates two or more constraints for a UI element to the parent layout. After you drag the element to the layout, it creates constraints based on the element's position.

✔ Drag a **Button** from the **Palette** pane to any position in the layout.



### 3. Add a second Button to the layout

1. Drag another **Button** from the **Palette** pane to the middle of the layout as shown in the animated figure below.
2. Drag a vertical constraint to the bottom of the layout (refer to the figure below).

![Adding a second button with constraints](https://developer.android.com/static/codelabs/android-training-layout-editor-part-a/img/f06e6a4442437213.gif?hl=ko)

You can remove constraints from an element by selecting the element and hovering your pointer over it to show the Clear Constraints ![image-20220907232658736](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.2 Part A Your first interactive UI.assets\image-20220907232658736.png) button. 

To clear all constraints in the entire layout, click the **Clear All Constraints** tool in the toolbar. This tool is useful if you want to redo all the constraints in your layout.



## 5. Task 3: Change UI element attributes

The **Attributes**(known as properties) pane offers access to all of the XML attributes you can assign to a UI element. 



### 1. Change the Button size

![image-20220907233249425](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.2 Part A Your first interactive UI.assets\image-20220907233249425.png)

![Sizing panel in the Attributes pane](https://developer.android.com/static/codelabs/android-training-layout-editor-part-a/img/cb766d399da0945d.png?hl=ko)

✔ **1. Height control**

 This control specifies the `layout_height` attribute and appears in two segments on the top and **bottom** sides of the square. 

The angles indicate that this control is set to `wrap_content`

* `View` will expand vertically as needed to fit its contents. 
* The "0" indicates a standard margin set to 0dp.



✔ **2. Width control**

This control specifies the `layout_width` and appears in two segments on the **left** and **right** sides of the square.

The angles indicate that this control is set to `wrap_content`

* which means the `View` will expand horizontally as needed to fit its contents, up to a margin of 0dp.



**ConstraintLayout**

- The `match_constraint` setting expands the `View` element to fill its parent by width or height—up to a margin, if one is set. The parent in this case is the `ConstraintLayout`.
- The `wrap_content` setting shrinks the `View` element's dimensions so it is just big enough to enclose its content. If there is no content, the `View` element becomes <u>invisible.</u>

❗ To specify a fixed size that adjusts for the screen size of the device, use a fixed number of density-independent pixels (`dp` units). 

* For example, `16dp` means 16 density-independent pixels.



### 2. Change the Button attributes

The **Attributes** pane offers access to all of the attributes you can assign to a `View` element. 

You can enter values for each attribute, such as the `android:id`, `backgroundTint`, `textColor`, and `text` attributes.

✔ android:id ➡ button_toast

✔ backgroundTint ➡  @color/purple_200

✔ textColor ➡ @android:color/black

✔ text ➡ Toast



The `color/purple_200` is one of the predefined theme base colors defined in the `colors.xml` resource file. The original default purple is used for the app bar



## 6. Task 4: Add a TextEdit and set its attributes

One of the benefits of `ConstraintLayout` is the ability to align or otherwise constrain elements relative to other elements. In this task you will add a `TextView` in the middle of the layout, and constrain it horizontally to the margins and vertically to the two `Button` elements. You will then change the attributes for the `TextView` in the **Attributes** pane.



### 1. Add a TextView and constraints

1. Drag a `TextView` from the **Palette** pane to the upper part of the layout, and drag a constraint from the top of the `TextView` to the handle on the bottom of the **Toast** `Button`.
2.  Drag a constraint from the bottom of the `TextView` to the handle on the top of the **Count** `Button`, and from the sides of the `TextView` to the sides of the layout.



### 2. Set the TextView attributes

1. Set the `ID` to **show_count**.
2. Change the horizontal and vertical view size controls (`layout_width` and `layout_height`) to **match_constraint**.
3. Set the `text` to **0**.
4. Scroll down the pane to **Common Attributes** and expand the textAppearance attribute.
5. Set the `textSize` to **160sp**.
6. Set the `textColor` to **@color/purple_500**.
7. Set the `textStyle` to **B** (bold).
8. Scroll down the pane to **All Attributes**, and set the `background` to **#FFFF00** for a shade of yellow.
9. Scroll down to `gravity`, expand `gravity`, and select **center**.

- `textSize`: The text size of the `TextView`. For this lesson, the size is set to `160sp`. The `sp` stands for *scale-independent pixel*, and like `dp`, is a unit that scales with the screen density and user's font size preference. Use dp units when you specify font sizes so that the sizes are adjusted for both the screen density and the user's preference.
- `textStyle`: The text style, set to **B** (bold) in this lesson
- `gravity`: The `gravity` attribute specifies how a `View` is aligned within its *parent* `View` or `ViewGroup`. In this step, you center the `TextView` to be centered within the parent `ConstraintLayout`.

![image-20220913233723375](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.2 Part A Your first interactive UI.assets\image-20220913233723375.png)



## 7. Task 5: Edit the layout in XML

An exclamation point appears next to each UI element in the **Component Tree**. 

Hover your pointer over these exclamation points to see warning messages, as shown below. The same warning appears for all three elements: hardcoded strings should use resources.

![Viewing UI element warnings](https://developer.android.com/static/codelabs/android-training-layout-editor-part-a/img/5e8d5d4a30120563.gif?hl=ko)



❗ The easiest way to fix layout problems is to edit the layout in XML. 

❗ While the layout editor is a powerful tool, some changes are easier to make directly in the XML source code.



### 1. Open the XML code for the layout

**activity_main.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/button_count"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:backgroundTint="@color/purple_200"
        android:text="Count"
        android:textColor="@android:color/black"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="1.0" />

    <Button
        android:id="@+id/button_toast"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:backgroundTint="@color/purple_200"
        android:text="Toast"
        android:textColor="@android:color/black"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.0" />

    <TextView
        android:id="@+id/show_count"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="#FFFF00"
        android:gravity="center"
        android:text="0"
        android:textColor="@color/purple_500"
        android:textSize="160sp"
        android:textStyle="bold"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

![image-20220913234148652](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.2 Part A Your first interactive UI.assets\image-20220913234148652.png)



### 2. Extract string resources

It is a best practice to use string resources, which represent the strings. 

Having the strings in a separate file makes it easier to manage them, especially if you use these strings more than once. 

Also, string resources are mandatory for translating and localizing your app, because you need to create a string resource file for each language.

`android:text="Toast"`➡`android:text="@string/toast"`

![image-20220913234313790](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.2 Part A Your first interactive UI.assets\image-20220913234313790.png)



You need another string to use in a subsequent task that displays a message. 

Add to the `strings.xml` file another string resource named `toast_message` for the phrase "Hello Toast!":

![image-20220913234947965](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.2 Part A Your first interactive UI.assets\image-20220913234947965.png)



## 8. Task 6: Add onClick handlers for the buttons

In this task, you add a Java method for each `Button` in `MainActivity` that executes when the user taps the `Button`.



### 1. Add the onClick attribute and handler to each Button

 In Android Studio you can specify the name of the method in the `onClick` field in the **Design** tab's **Attributes** pane.

 You can also specify the name of the handler method in the XML editor by adding the `android:onClick` property to the `Button`. 



1. With the XML editor open (the Code tab), find the `Button` with the `android:id` set to `button_toast`:

   ```xml
       <Button
           android:id="@+id/button_count"
           android:layout_width="0dp"
           android:layout_height="wrap_content"
           android:backgroundTint="@color/purple_200"
           app:layout_constraintBottom_toBottomOf="parent"
           app:layout_constraintEnd_toEndOf="parent"
           app:layout_constraintHorizontal_bias="0.0"
           app:layout_constraintStart_toStartOf="parent"
           app:layout_constraintTop_toTopOf="parent"
           app:layout_constraintVertical_bias="1.0" />

2. Add the `android:onClick` attribute to the end of the `button_toast` element after the last attribute and before the `/>` end indicator.
3. Click the red bulb icon that appears next to the attribute. Select **Create ‘showToast(View)' in ‘MainActivity'**.

![image-20220913235537949](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.2 Part A Your first interactive UI.assets\image-20220913235537949.png)

**MainActivity.java**

```java
package com.example.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToast(View view) {
    }
}
```



4. Return to `activity_main.xml` and repeat the last two steps with the `button_count` `Button`.

   ```xml
     <Button
         android:id="@+id/button_toast"
   		...
         android:onClick="showToast"/>
     <Button
         android:id="@+id/button_count"
   		...
         android:onClick="countUp" />
   ```
   
   

### 2. Edit the Toast Button handler

Edit the `showToast()` method - **Toast** `Button` click handler in **MainActivity.java**

1. Locate the newly created `showToast()` method.
2. To create an instance of a `Toast`, call the `makeText()`factory method on the `Toast`class.
3. Supply the context of the app `Activity`. 
   * Because a Toast displays on top of the Activity UI, the system needs information about the current Activity. 
4. Supply the message to display, such as a string resource (the `toast_message` you created in a previous step).
5. Supply a duration for the display. 
   * `Toast.LENGTH_SHORT` displays the toast for a relatively short time.
6. Show the `Toast` by calling `show()`

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);

        toast.show();
    }

    public void countUp(View view) {
        mCount++;
        if (mShowCount != null) {
            mShowCount.setText(Integer.toString(mCount));
        }
    }
```



### 3. Edit the Count Button handler

Edit the  `countUp()` method—the **Count** `Button`click handler in **MainActivity.java**

so that it displays the current count after **Count** is tapped. Each tap increases the count by one.

1. Locate the newly created `countUp()` method.
2. Each tap of the **Count** button increases the value of this variable. Enter the following, which will be highlighted in red and show a red bulb icon:
3. Click the red bulb icon and choose Create field `mCount` in **MainActivity.java** from the popup menu. 
   * This creates a private member variable at the top of **MainActivity.java**, and Android Studio assumes that you want it to be an integer (int).
4. Change the private member variable statement to initialize the variable to zero.
5. `TextView`, which you will add to the click handler. Call this variable `mShowCount`
6. Now that you have `mShowCount`, you can get a reference to the **TextView** using the ID you set in the layout file.
7. Add the `findViewById` statement to the end of the method.
8. We have assigned to `mShowCount` the **TextView**, you can use the variable to set the text in the **TextView** to the value of the `mCount` variable.

**MainActivity.java**

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
    }

    public void countUp(View view) {
        mCount++;
        if (mShowCount != null) {
            mShowCount.setText(Integer.toString(mCount));
        }
    }
```



## 9. Coding challenge

The HelloToast app looks fine when the device or emulator is vertically oriented. However, if you switch the device or emulator to horizontal orientation, the **Count** `Button` may overlap the `TextView` along the bottom.

**Challenge**: Change the layout so that it looks good in both horizontal and vertical orientations:



## Summary

View, ViewGroup, and layouts:

* All UI elements are subclasses of the `View` class and therefore inherit many properties of the `View` superclass. 
* `View` elements can be grouped inside a `ViewGroup`, which acts as a container. The relationship is parent-child, in which the parent is a `ViewGroup`, and the child is a `View` or another `ViewGroup`. 
* The `onCreate()` method is used to inflate the layout, which means to set the content view of the screen to the XML layout. You can also use it to get references to other UI elements in the layout. 
* A `View`, like a string, is a resource that can have an id. The `findViewById` call takes the ID of a view as its parameter and returns the `View`.

✔ Using the layout editor

✔ Setting layout width and height

✔ Extracting string resources

✔ Handling clicks
