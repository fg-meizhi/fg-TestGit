rem winmail���ûָ�����
rem =====================================

set /p conf_date=������ָ������ڣ����磺2015-12-31��

"C:\Program Files (x86)\WinRAR\WinRAR.exe" x "E:\Programs\winmail\backup\%conf_date%_conf.zip" "E:\Programs\winmail\backup\%conf_date%_conf\"
xcopy "E:\Programs\winmail\backup\%conf_date%_conf" "C:\Program Files\Magic Winmail\server\" /Y /S /E
rmdir "E:\Programs\winmail\backup\%conf_date%_conf\" /S /Q