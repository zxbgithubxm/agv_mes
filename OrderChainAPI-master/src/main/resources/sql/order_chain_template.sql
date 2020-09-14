-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- 主机： mysqlsrv
-- 生成日期： 2020-08-11 13:38:17
-- 服务器版本： 5.7.28-log
-- PHP 版本： 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `dispatcher`
--

-- --------------------------------------------------------

--
-- 表的结构 `order_chain_template`
--

CREATE TABLE `order_chain_template` (
  `ID` bigint(20) UNSIGNED NOT NULL COMMENT '任务链模板主键',
  `NAME` varchar(30) NOT NULL COMMENT '任务链模板名称',
  `GROUP_ID` bigint(20) NOT NULL COMMENT '所属分组主键，对应车辆分组',
  `PRIORITY` int(3) NOT NULL COMMENT '任务链优先级',
  `DOMAIN_ID` bigint(20) NOT NULL COMMENT '所属区域ID',
  `WORKER_ID` bigint(20) DEFAULT NULL COMMENT '指定设备ID',
  `ENABLE` tinyint(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '启用禁用标识',
  `DELETE_FLAG` int(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否删除',
  `INSERT_TIME` datetime NOT NULL COMMENT '记录时间',
  `CYCLE` tinyint(1) DEFAULT '0' COMMENT '是否循环'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务链模板配置信息' ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `order_chain_template`
--

INSERT INTO `order_chain_template` (`ID`, `NAME`, `GROUP_ID`, `PRIORITY`, `DOMAIN_ID`, `WORKER_ID`, `ENABLE`, `DELETE_FLAG`, `INSERT_TIME`, `CYCLE`) VALUES
(24, '展示-上下料', 8, 1, 3, NULL, 0, 0, '2020-07-23 10:35:21', 1),
(25, '柒牌测试-02', 8, 1, 3, NULL, 0, 0, '2020-07-23 15:02:21', 0),
(26, '展示-移动', 8, 1, 3, NULL, 0, 0, '2020-07-28 22:48:18', 1),
(27, '测试大幅度发', 8, 0, 3, NULL, 0, 0, '2020-07-29 08:54:36', 1),
(28, '5G测试', 8, 0, 3, NULL, 0, 0, '2020-07-29 10:02:02', 1),
(29, 'ceshif', 8, 1, 3, NULL, 0, 0, '2020-08-04 11:59:58', 1),
(30, '6F地图测试', 8, 1, 3, NULL, 0, 0, '2020-08-07 09:14:51', 1),
(31, '6F地图测试22', 8, 1, 3, NULL, 0, 0, '2020-08-07 17:00:26', 0),
(32, '6F地图测试30', 8, 1, 3, NULL, 0, 0, '2020-08-08 16:18:04', 1),
(33, '演示測試', 8, 1, 3, NULL, 0, 0, '2020-08-11 10:21:14', 1),
(34, '6FSL30', 8, 0, 3, NULL, 0, 0, '2020-08-11 21:26:02', 0),
(35, '4FXL1', 8, 0, 3, NULL, 0, 0, '2020-08-11 21:29:54', 0);

--
-- 转储表的索引
--

--
-- 表的索引 `order_chain_template`
--
ALTER TABLE `order_chain_template`
  ADD PRIMARY KEY (`ID`) USING BTREE;

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `order_chain_template`
--
ALTER TABLE `order_chain_template`
  MODIFY `ID` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '任务链模板主键', AUTO_INCREMENT=36;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
