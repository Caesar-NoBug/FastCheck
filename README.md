# FastCheck
声明式对象属性校验器

## 下载

[1.0.0测试版](https://github.com/Caesar-NoBug/FastCheck/releases/tag/1.0.0)

## 功能

- 声明式实体参数校验
- 字符串工具类
- 自定义参数校验方法
- 自动抛出异常并附带错误信息

## 快速开始

```java
@Data
public class Example {
    
    //限制名称最大长度为10
    @StringChecker(name = "用户名", maxLength = 10)
    private String name;
    
    //限制年龄在1-100之间
    @NumberChecker(name = "年龄", min = 1, max = 100)
    private Integer age;
    
    @StringChecker(name = "邮箱", format = StrFormat.EMAIL)
    private String email;

    public static void main(String[] args) {
        Example example = new Example();
        example.setName("caesar");
        example.setAge(110);
        example.setEmail("123@qq.com");
        
        // 校验
        CheckManager.checkThrowException(example, Example.class);
        
    }
    
}
```
