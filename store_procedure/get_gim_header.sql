DELIMITER $$
CREATE PROCEDURE GET_GIM_HEADER()
BEGIN
    select * from tb_m_gim_h;
END$$
DELIMITER ;