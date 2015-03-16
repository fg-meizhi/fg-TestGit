set /p appdata_date=请输入恢复至日期（例如：20151231）
xcopy "F:\Backup\Confluence\Confluence_data\%appdata_date%" "E:\Programs\Confluence_data" /Y /S /E