# https://leetcode-cn.com/problems/duplicate-emails/
# 编写一个 SQL 查询，查找 Person 表中所有重复的电子邮箱。
#
# 示例：
#
# +----+---------+
# | Id | Email   |
# +----+---------+
# | 1  | a@b.com |
# | 2  | c@d.com |
# | 3  | a@b.com |
# +----+---------+
# 根据以上输入，你的查询应返回以下结果：
#
# +---------+
# | Email   |
# +---------+
# | a@b.com |
# +---------+
# 说明：所有电子邮箱都是小写字母

#思路：重复，意味着，出现次数>1，此时应该想到分组后count()
# COUNT()函数可以跟具体的列名。且允许去重.COUNT( DISTINCT email)>1;

USE leetcode;

SELECT Email
FROM Person
GROUP BY Email
HAVING COUNT(  email)>1;
