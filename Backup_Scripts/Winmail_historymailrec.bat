rem winmail�û��ʼ��ָ�����
rem =====================================

set /p conf_month=������ָ����·ݣ����磺2015-12��

for /f "tokens=1 delims=@" %%1 in ('dir E:\Programs\winmail\backup\%conf_month% /b') do (
	xcopy "E:\Programs\winmail\backup\%conf_month%\%%1@boc.ibm\received" "E:\Programs\winmail\store\%%1\INBOX\" /Y /D
	xcopy "E:\Programs\winmail\backup\%conf_month%\%%1@boc.ibm\sent" "E:\Programs\winmail\store\%%1\Sent\" /Y /D
)