# https://leetcode-cn.com/problems/rising-temperature/
# 给定一个 Weather 表，编写一个 SQL 查询，来查找与之前（昨天的）日期相比温度更高的所有日期的 Id。
#
# +---------+------------------+------------------+
# | Id(INT) | RecordDate(DATE) | Temperature(INT) |
# +---------+------------------+------------------+
# |       1 |       2015-01-01 |               10 |
# |       2 |       2015-01-02 |               25 |
# |       3 |       2015-01-03 |               20 |
# |       4 |       2015-01-04 |               30 |
# +---------+------------------+------------------+
# 例如，根据上述给定的 Weather 表格，返回如下 Id:
#
# +----+
# | Id |
# +----+
# |  2 |
# |  4 |
# +----+

# 日期计算，datediff函数，定量的日期关系，应该直接作为连接条件 ON
USE leetcode;

SELECT T.Id AS 'Id'
FROM weather AS T INNER  JOIN  weather AS B
ON DATEDIFF(T.RecordDate,B.RecordDate)=1
WHERE T.Temperature>B.Temperature;