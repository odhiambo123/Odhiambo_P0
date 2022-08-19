DELIMITER //
CREATE PROCEDURE list_all_my_employees()
BEGIN
    select * from employee;
END //

DELIMITER ;

