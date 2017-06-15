#!/bin/sh

## 将 xml 转化为 xsd 文件,需要依赖 trang 包
# java -jar ~/.m2/repository/com/thaiopensource/trang/20091111/trang-20091111.jar ./ref.xml ref.xsd

## 将 xsd 文件转化为 java 实体类
xjc -d src/main/java -p xyz.liuzm.accumulation.developer_platform.xml src/main/resources/developer_platform.xsd
