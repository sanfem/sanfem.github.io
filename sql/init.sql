-- ============================================================
-- 个人JavaWeb网站数据库初始化脚本
-- 数据库: personal_site
-- ============================================================

CREATE DATABASE IF NOT EXISTS personal_site
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE personal_site;

-- -----------------------------------------------------------
-- 代码集表 (codes)
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS codes (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(200)  NOT NULL COMMENT '代码标题',
    language    VARCHAR(50)   NOT NULL COMMENT '编程语言',
    description TEXT          COMMENT '代码描述',
    content     LONGTEXT      NOT NULL COMMENT '代码内容',
    created_at  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='代码集';

-- -----------------------------------------------------------
-- 相册表 (photos)
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS photos (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(200)  NOT NULL COMMENT '照片标题',
    description TEXT          COMMENT '照片描述',
    file_path   VARCHAR(500)  NOT NULL COMMENT '文件存储路径',
    created_at  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='相册';

-- -----------------------------------------------------------
-- 日记表 (diaries)
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS diaries (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(200)  NOT NULL COMMENT '日记标题',
    content     LONGTEXT      NOT NULL COMMENT '日记内容',
    mood        VARCHAR(50)   COMMENT '心情',
    weather     VARCHAR(50)   COMMENT '天气',
    created_at  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='日记';

-- -----------------------------------------------------------
-- 示例数据
-- -----------------------------------------------------------
INSERT INTO codes (title, language, description, content) VALUES
('Hello World', 'Java', '一个简单的Java Hello World程序',
'public class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}'),
('冒泡排序', 'Java', '经典的冒泡排序算法实现',
'public class BubbleSort {\n    public static void sort(int[] arr) {\n        int n = arr.length;\n        for (int i = 0; i < n - 1; i++) {\n            for (int j = 0; j < n - i - 1; j++) {\n                if (arr[j] > arr[j + 1]) {\n                    int temp = arr[j];\n                    arr[j] = arr[j + 1];\n                    arr[j + 1] = temp;\n                }\n            }\n        }\n    }\n}'),
('快速排序', 'Python', 'Python实现的快速排序',
'def quicksort(arr):\n    if len(arr) <= 1:\n        return arr\n    pivot = arr[len(arr) // 2]\n    left = [x for x in arr if x < pivot]\n    middle = [x for x in arr if x == pivot]\n    right = [x for x in arr if x > pivot]\n    return quicksort(left) + middle + quicksort(right)');

INSERT INTO diaries (title, content, mood, weather) VALUES
('开始搭建个人网站', '今天开始搭建自己的个人网站，使用JSP + MySQL + Tomcat技术栈。希望能做出一个漂亮实用的网站！', '开心', '晴天'),
('学习JSP技术', '今天深入学习了JSP技术，包括EL表达式和JSTL标签库的使用。收获很大！', '充实', '多云');
