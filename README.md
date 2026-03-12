# 个人JavaWeb网站

一个基于 **MySQL + JSP + Tomcat 7** 的个人网站，用于展示代码集、相册和日记。

## 功能介绍

- **代码集** - 收藏和展示代码片段，支持多种编程语言，可添加、编辑、删除
- **相册** - 上传和浏览照片，支持图片上传和删除
- **日记** - 记录日常生活，支持心情和天气标签，可增删改查

## 技术栈

| 技术 | 说明 |
|------|------|
| JSP | 页面展示层 |
| Servlet | 控制层，处理HTTP请求 |
| MySQL | 数据存储 |
| JSTL | JSP标签库 |
| Tomcat 7 | Web服务器 |
| JDBC | 数据库连接 |
| Font Awesome | 图标库 |

## 项目结构

```
├── sql/
│   └── init.sql                          # 数据库初始化脚本
├── src/main/
│   ├── java/com/sanfem/
│   │   ├── model/                        # 模型类
│   │   │   ├── Code.java
│   │   │   ├── Photo.java
│   │   │   └── Diary.java
│   │   ├── dao/                          # 数据访问层
│   │   │   ├── CodeDAO.java
│   │   │   ├── PhotoDAO.java
│   │   │   └── DiaryDAO.java
│   │   ├── servlet/                      # 控制层
│   │   │   ├── CodeServlet.java
│   │   │   ├── PhotoServlet.java
│   │   │   └── DiaryServlet.java
│   │   └── util/                         # 工具类
│   │       ├── DBUtil.java
│   │       └── CharacterEncodingFilter.java
│   └── webapp/
│       ├── WEB-INF/web.xml               # Web应用配置
│       ├── css/style.css                 # 样式表
│       ├── js/main.js                    # JavaScript
│       ├── jsp/                          # JSP页面
│       │   ├── header.jsp                # 公共头部
│       │   ├── footer.jsp                # 公共底部
│       │   ├── code/                     # 代码集页面
│       │   ├── photo/                    # 相册页面
│       │   ├── diary/                    # 日记页面
│       │   └── error/                    # 错误页面
│       ├── uploads/photos/               # 照片上传目录
│       └── index.jsp                     # 首页
├── index.html                            # 静态首页（GitHub Pages）
└── README.md
```

## 部署步骤

### 1. 环境准备

- **JDK 7+**
- **Apache Tomcat 7**
- **MySQL 5.6+**

### 2. 数据库配置

```bash
# 登录MySQL并执行初始化脚本
mysql -u root -p < sql/init.sql
```

### 3. 修改数据库连接

编辑 `src/main/java/com/sanfem/util/DBUtil.java`，修改以下配置为你的数据库信息：

```java
private static final String URL = "jdbc:mysql://localhost:3306/personal_site?useUnicode=true&characterEncoding=utf8";
private static final String USERNAME = "root";
private static final String PASSWORD = "root";
```

### 4. 添加依赖JAR包

将以下JAR包放入 `WEB-INF/lib/` 目录：

- `mysql-connector-java-5.1.xx.jar` (MySQL JDBC驱动)
- `jstl-1.2.jar` (JSTL标签库)

### 5. 编译和部署

将项目编译后的文件部署到Tomcat的 `webapps` 目录下，或在IDE中直接配置Tomcat服务器运行。

### 6. 启动

启动Tomcat服务器，访问 `http://localhost:8080/你的项目名/` 即可。

## 页面预览

- **首页** - 展示三大功能模块入口
- **代码集** - 以列表形式展示代码，点击查看详情和源码
- **相册** - 以网格形式展示照片，支持上传和删除
- **日记** - 以时间线形式展示日记，可记录心情和天气
