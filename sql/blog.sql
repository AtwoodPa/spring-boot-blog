-- 用户表
CREATE TABLE IF NOT EXISTS users (
                                     user_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
                                     username VARCHAR(255) NOT NULL COMMENT '用户名',
    password VARCHAR(255) NOT NULL COMMENT '用户密码',
    email VARCHAR(255) NOT NULL COMMENT '用户邮箱',
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间'
    ) COMMENT='用户表';

-- 文章表
CREATE TABLE IF NOT EXISTS posts (
                                     post_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '文章ID',
                                     title VARCHAR(255) NOT NULL COMMENT '文章标题',
    content TEXT NOT NULL COMMENT '文章内容',
    user_id BIGINT NOT NULL COMMENT '用户ID（不设置外键约束）',
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间'
    ) COMMENT='文章表';

-- 插入用户表的模拟数据
INSERT INTO users (username, password, email, created, last_modified) VALUES
                                                                          ('zhangsan', '$2a$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36o4i8XcU9E/9X3oV3WjF96', 'zhangsan@example.com', '2023-01-15 08:45:00', '2023-01-15 08:45:00'),
                                                                          ('lisi', '$2a$10$4jG8V4l8TjkH/jM8wZ0J7OUVxdoP0n1vhdT8mU0eL5O6Q2z3Y5S8e', 'lisi@example.com', '2023-02-20 12:30:00', '2023-02-20 12:30:00'),
                                                                          ('wangwu', '$2a$10$3dHGVnv.GjY8iXUVa6ZVuO.gJkEzUGhfz6.zq.wz/mIz57ZKpHDvO', 'wangwu@example.com', '2023-03-25 10:15:00', '2023-03-25 10:15:00');

-- 插入文章表的模拟数据
INSERT INTO posts (title, content, user_id, created, last_modified) VALUES
                                                                        ('Spring Boot 入门', 'Spring Boot 让创建独立的、生产级 Spring 应用程序变得容易，只需“运行”即可。', 1, '2023-01-16 09:00:00', '2023-01-16 09:00:00'),
                                                                        ('理解 REST API', 'REST API（也称为 RESTful API）是一种符合 REST 架构风格约束的应用程序编程接口（API 或 Web API）。', 2, '2023-02-21 14:00:00', '2023-02-21 14:00:00'),
                                                                        ('微服务介绍', '微服务架构是一种架构风格，它将应用程序构建为一组小的自治服务。', 3, '2023-03-26 11:00:00', '2023-03-26 11:00:00'),
                                                                        ('Docker 指南', 'Docker 是一个开发、运输和运行应用程序的开放平台。Docker 使您能够将应用程序与基础架构分离，以便快速交付软件。', 1, '2023-01-17 10:00:00', '2023-01-17 10:00:00'),
                                                                        ('学习 Kubernetes', 'Kubernetes 是一个开源系统，用于自动化容器化应用程序的部署、扩展和管理。', 2, '2023-02-22 15:00:00', '2023-02-22 15:00:00'),
                                                                        ('高级 Java 编程', 'Java 是一种高级、基于类的、面向对象的编程语言，设计时尽量减少实现依赖。', 3, '2023-03-27 12:00:00', '2023-03-27 12:00:00');
