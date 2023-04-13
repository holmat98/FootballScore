call ./gradlew :app:bundleDebug
echo App bundle created
IF EXIST app/build/outputs/bundle/debug/app-debug.apks (
    pushd .
    cd app/build/outputs/bundle/debug/
    del app-debug.apks
    popd
    echo File app-debug.apks deleted
)
call java -jar bundletool-all-1.14.0.jar build-apks --local-testing --bundle=app/build/outputs/bundle/debug/app-debug.aab --output=app/build/outputs/bundle/debug/app-debug.apks
echo File app-debug.apks created
call java -jar bundletool-all-1.14.0.jar install-apks --apks=app/build/outputs/bundle/debug/app-debug.apks
echo App installed on the device
call adb shell am start -n com.mateuszholik.footballscore/com.mateuszholik.footballscore.ui.MainActivity
echo App hast started