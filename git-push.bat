@echo off
title = Push to Git...
@cd %~dp0
@echo off
git status
git add .
git commit -m "Initial commit"
git push origin master
pause