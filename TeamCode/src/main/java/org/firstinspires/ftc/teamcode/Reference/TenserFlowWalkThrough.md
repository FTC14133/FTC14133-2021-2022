# TenserFLow Walk Through

## Intro

Go to [FTC-ML on firstinspires](https://ftc-ml.firstinspires.org)

Team Shipping Element (TSE) can be a card board box painted a vibrant color with multipule colors, stipes, patterns, ect.
- Min - 3x3x4
- Max - 4x4x8

## Recording

1. When taking videos take it at the same postition as where you will be starting at during auto
2. Record with different backrounds, lighting conditions, and different TSE positions
3. When Recording it has to be in different starting positions for the robot (Red side, Warehouse side)
4. Include shots without TSE
5. When taking the video as you move it pause the video
6. Video length should be no more than 20 seconds (Can be more but will make us lose hours we can work on it)

## Uploading

1. Upload video to FTC website
2. Click on video
3. Draw bounding box over TSE
4. Make sure to label it TSE (Very Important! If one is incorrect the whole thing will break!)
5. Draw the bounding box for about 5 more
6. Press the start tracking button to speed up the procces
7. After a major change in the video it might lose sight of the TSE but say it is still there; You will have to change it.

## Preping Data

1. When done it will save by it self
2. Go to home page
3. Select video
4. Click on "Produce Dataset"
5. Description is the Name (Does not have to be TSE; "TSE Video" is good)
6. Go to the tab "Datasets"
7. Click on the data set you made
8. Click "Start Training"
9. Change the training steps so that the epochs can be around 100
10. You can see the epochs at the bottom

## Downloading TenserData and Uploading it Robot
Once the model is done training go to the Models tab

1. Click on the model
2. Click "Download Model"
3. Connect to robot
4. Search up [http://192.168.43.1/]() on google
5. Go to Manage
6. Scroll sown to "Upload TenserFlow Lite Model File"
7. Upload the ".tflite" file


### Work Cited
[INFO](https://youtu.be/LGSL5izjCKU)
