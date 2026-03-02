# 学生管理系统 - Student Management System
基于 Spring Boot 构建的企业级规范后端项目，完整实现学生信息的CRUD管理，具备标准化接口输出、在线接口文档、JWT登录鉴权等工业级特性。

## 项目亮点
- 严格遵循 MVC 分层架构，职责边界清晰，代码可维护性、可扩展性强
- 基于 OpenAPI 3.0 规范（SpringDoc）实现自动生成在线可调试接口文档
- 封装全局统一响应结果，实现接口输出标准化，兼容异常场景处理
- 基于 JWT 实现轻量级登录认证与接口鉴权，保障后端接口访问安全
- 基于 Spring Data JPA 实现数据库操作，简化持久层代码开发

## 技术栈
| 技术/组件 | 版本/说明 |
|-----------|-----------|
| 核心框架 | Spring Boot 2.x/3.x |
| 持久层框架 | Spring Data JPA |
| 接口文档 | SpringDoc OpenAPI 3.0 |
| 认证鉴权 | JWT（java-jwt） |
| 开发工具 | Lombok |
| 数据库 | MySQL 8.0+（可适配H2内存数据库） |
| 构建工具 | Maven |

## 本地启动步骤
### 1. 环境准备
- JDK 8+（Spring Boot 3.x 需要 JDK 17+）
- Maven 3.6+
- MySQL 8.0+（可选，默认可使用H2内存数据库无需额外配置）

### 2. 克隆项目到本地
```bash
git clone https://github.com/ZonEn123/student-management.git
cd student-management