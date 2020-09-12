# https://leetcode-cn.com/problems/classes-more-than-5-students/
# 有一个courses 表 ，有: student (学生) 和 class (课程)。
#
# 请列出所有超过或等于5名学生的课。
#
# 例如,表:
#
# +---------+------------+
# | student | class      |
# +---------+------------+
# | A       | Math       |
# | B       | English    |
# | C       | Math       |
# | D       | Biology    |
# | E       | Math       |
# | F       | Computer   |
# | G       | Math       |
# | H       | Math       |
# | I       | Math       |
# +---------+------------+
# 应该输出:
#
# +---------+
# | class   |
# +---------+
# | Math    |
# +---------+
# Note:
# 学生在每个课中不应被重复计算。

# DISTINCT 可以直接作用在列名上，完成去重的作用，然后后使用count计数！

USE leetcode;

SELECT class
FROM courses
GROUP BY class
HAVING COUNT(DISTINCT student)>5;