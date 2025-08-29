@echo off
echo ===============================
echo Iniciando a aplicacao BibliotecaFXML
echo ===============================

rem Caminho do repositório Maven local
set "M2_REPO=%USERPROFILE%\.m2\repository"

rem Caminho para o JAR do projeto
set "JAR_PATH=target\BibliotecaFXML-1.0-SNAPSHOT.jar"

rem Classe principal (substitua pelo nome correto)
set "MAIN_CLASS=com.alana.bibliotecafxml.HelloApplication"

rem Verifica se o JAR existe
if not exist "%JAR_PATH%" (
    echo ERRO: JAR nao encontrado em %JAR_PATH%
    echo Execute "mvn clean package" antes de rodar este script.
    pause
    exit /b
)

rem Lista de módulos JavaFX e dependências
set "MODULE_PATH=%M2_REPO%\org\openjfx\javafx-controls\17.0.6\javafx-controls-17.0.6-win.jar;%M2_REPO%\org\openjfx\javafx-fxml\17.0.6\javafx-fxml-17.0.6-win.jar;%M2_REPO%\org\openjfx\javafx-base\17.0.6\javafx-base-17.0.6-win.jar;%M2_REPO%\org\openjfx\javafx-graphics\17.0.6\javafx-graphics-17.0.6-win.jar;%M2_REPO%\org\kordamp\bootstrapfx\bootstrapfx-core\0.4.0\bootstrapfx-core-0.4.0.jar"

rem Módulos a adicionar
set "ADD_MODULES=javafx.controls,javafx.fxml"

echo Executando aplicacao...
java --module-path "%MODULE_PATH%" --add-modules %ADD_MODULES% -cp "%JAR_PATH%" %MAIN_CLASS%

echo ===============================
echo Aplicacao finalizada.
pause