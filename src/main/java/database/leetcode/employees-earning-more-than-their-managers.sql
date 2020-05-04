# https://leetcode-cn.com/problems/employees-earning-more-than-their-managers/

# Employee 表包含所有员工，他们的经理也属于员工。每个员工都有一个 Id，此外还有一列对应员工的经理的 Id。
#
# +----+-------+--------+-----------+
# | Id | Name  | Salary | ManagerId |
# +----+-------+--------+-----------+
# | 1  | Joe   | 70000  | 3         |
# | 2  | Henry | 80000  | 4         |
# | 3  | Sam   | 60000  | NULL      |
# | 4  | Max   | 90000  | NULL      |
# +----+-------+--------+-----------+
# 给定 Employee 表，编写一个 SQL 查询，该查询可以获取收入超过他们经理的员工的姓名。在上面的表格中，Joe 是唯一一个收入超过他的经理的员工。
#
# +----------+
# | Employee |
# +----------+
# | Joe      |
# +----------+

# 思路：收入超过经理的员工，连接条件应该是ManagerId,只有这样才能确保连接到的是经理！！！
# 当字段值与表名相同时，应该注明其字符串属性
USE leetcode;

SELECT E.Name as 'Employee'
FROM  Employee as E
    Inner JOIN Employee as M
    ON E.ManagerId=M.Id AND E.Salary >M.Salary;