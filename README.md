# composite-java-cpp

## 1.SWT拡張パッケージインストール
```
JAVA_HOME=`/usr/libexec/java_home -v 1.8`

mvn install:install-file \
    -Dfile=$JAVA_HOME/jre/lib/jfxswt.jar \
    -DgroupId=javafx.embed \
    -DartifactId=swt \
    -Dversion=1.8.0_241 \
    -Dpackaging=jar \
    -DgeneratePom=true
```

## 2.SWT(SWT Binary and Source)パッケージインストール
https://download.eclipse.org/eclipse/downloads/drops4/R-4.15-202003050155/

[swt-4.15-cocoa-macosx-x86_64.zip をダウンロード](https://download.eclipse.org/eclipse/downloads/drops4/R-4.15-202003050155/download.php?dropFile=swt-4.15-cocoa-macosx-x86_64.zip)
```
cd Downloads/swt-4.15-cocoa-macosx-x86_64

mvn install:install-file \
    -Dfile=swt.jar \
    -DgroupId=org.eclipse.swt \
    -DartifactId=cocoa.macosx.x86_64 \
    -Dversion=4.15 \
    -Dpackaging=jar \
    -DgeneratePom=true
```
