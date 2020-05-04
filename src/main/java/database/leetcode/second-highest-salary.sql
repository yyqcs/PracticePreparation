# https://leetcode-cn.com/problems/second-highest-salary/
# 176. 第二高的薪水
# 编写一个 SQL 查询，获取 Employee 表中第二高的薪水（Salary） 。
#
# +----+--------+
# | Id | Salary |
# +----+--------+
# | 1  | 100    |
# | 2  | 200    |
# | 3  | 300    |
# +----+--------+
# 例如上述 Employee 表，SQL查询应该返回 200 作为第二高的薪水。如果不存在第二高的薪水，那么查询应返回 null。
#
# +---------------------+
# | SecondHighestSalary |
# +---------------------+
# | 200                 |
# +---------------------+

# 使用函数IFNULL.空值作为判断条件，
USE leetcode;


# ifnull(x，y)，若x不为空则返回x，否则返回y;
# TOPk问题，应该逆序排列！！。
# 表达整体语义：使用圆括号"()"
# Topk,应该考虑去重操作。DISTINCT!
# Topk,LIMIT.

SELECT IFNULL((SELECT DISTINCT Salary
               FROM Employee
               ORDER BY Salary DESC
               LIMIT 1,1),NULL) AS SecondHighestSalary;