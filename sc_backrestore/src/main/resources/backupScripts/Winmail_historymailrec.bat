rem winmail用户邮件恢复部分
rem =====================================

set /p conf_month=请输入恢复至月份（例如：2015-12）

for /f "tokens=1 delims=@" %%1 in ('dir E:\Programs\winmail\backup\%conf_month% /b') do (
	xcopy "E:\Programs\winmail\backup\%conf_month%\%%1@boc.ibm\received" "E:\Programs\winmail\store\%%1\INBOX\" /Y /D
	xcopy "E:\Programs\winmail\backup\%conf_month%\%%1@boc.ibm\sent" "E:\Programs\winmail\store\%%1\Sent\" /Y /D
)