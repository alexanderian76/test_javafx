all:
	javac --module-path ../../../../javafx-sdk-20.0.1/lib --add-modules javafx.controls,javafx.fxml AppFX.java
	java --module-path ../../../../javafx-sdk-20.0.1/lib --add-modules javafx.controls,javafx.fxml AppFX