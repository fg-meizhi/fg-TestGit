rem 时间消耗 计算部分
rem =============================
@echo off
set /a starttime=(%time:~0,2%*3600)+(%time:~3,2%*60)+%time:~6,2%

rem ===========================================================
rem 执行一些耗时的行为（如：mysqldump, xcopy，调整系统时间等）
pause
rem ===========================================================

set /a consumer=(%time:~0,2%*3600)+(%time:~3,2%*60)+%time:~6,2%-%starttime%
set /a hour=%consumer%/3600
set /a min=(%consumer%%%3600)/60
set /a sec=(%consumer%%%3600)%%60
echo 总耗时：%hour%小时%min%分%sec%秒