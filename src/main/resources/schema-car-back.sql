CREATE TABLE IF NOT EXISTS `tb_http_log_out` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `req_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `req_method` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `req_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `req_params` text COLLATE utf8mb4_unicode_ci,
  `resp_code` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `resp_result` text COLLATE utf8mb4_unicode_ci,
  `resp_time` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `stat` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;