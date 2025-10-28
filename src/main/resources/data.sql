-- Dữ liệu mẫu

-- =============================================
-- Bảng Khoa (faculties) - Không có FK
-- =============================================
INSERT INTO faculties (faculty_name, dean, phone, email, description, address) VALUES
                                                                                   ('Công nghệ Thông tin', 'GS. TS. Trần Minh Tâm', '0912345678', 'cntt@university.edu.vn', 'Đào tạo kỹ sư phần mềm, mạng máy tính và khoa học dữ liệu.', 'Nhà A1, Khu Công nghệ cao'),
                                                                                   ('Kinh tế', 'PGS. TS. Lê Thị Mai', '0987654321', 'kinhte@university.edu.vn', 'Đào tạo cử nhân quản trị kinh doanh, tài chính ngân hàng.', 'Nhà B2, Khu Trung tâm'),
                                                                                   ('Kỹ thuật Cơ khí', 'TS. Phạm Văn Hùng', '0911223344', 'cokhi@university.edu.vn', 'Đào tạo kỹ sư cơ khí chế tạo máy, cơ điện tử.', 'Nhà C3, Khu Thực nghiệm'),
                                                                                   ('Luật', 'ThS. Hoàng Thị Lan', '0955667788', 'luat@university.edu.vn', 'Đào tạo cử nhân luật kinh tế, luật dân sự.', 'Nhà D4, Khu Hành chính'),
                                                                                   ('Ngoại ngữ', 'TS. Nguyễn Anh Tuấn', '0933445566', 'ngoaingu@university.edu.vn', 'Đào tạo cử nhân ngôn ngữ Anh, Nhật, Hàn.', 'Nhà E5, Khu Giảng đường');

-- =============================================
-- Bảng Môn học (subjects) - FK -> faculties
-- =============================================
-- Khoa CNTT (faculty_id = 1)
INSERT INTO subjects (subject_name, credits, description, faculty_id) VALUES
                                                                          ('Nhập môn lập trình', 3, 'Giới thiệu về lập trình C++', 1),
                                                                          ('Cấu trúc dữ liệu và giải thuật', 4, 'Các cấu trúc dữ liệu cơ bản và thuật toán', 1),
                                                                          ('Cơ sở dữ liệu', 3, 'Thiết kế và quản lý cơ sở dữ liệu quan hệ', 1),
                                                                          ('Mạng máy tính', 3, 'Các giao thức và kiến trúc mạng', 1),
                                                                          ('Hệ điều hành', 3, 'Nguyên lý hoạt động của hệ điều hành', 1),
                                                                          ('Phát triển web', 3, 'Lập trình frontend và backend', 1),
                                                                          ('Trí tuệ nhân tạo', 3, 'Các khái niệm cơ bản về AI', 1),
                                                                          ('An toàn thông tin', 3, 'Bảo mật hệ thống và mạng', 1);

-- Khoa Kinh tế (faculty_id = 2)
INSERT INTO subjects (subject_name, credits, description, faculty_id) VALUES
                                                                          ('Kinh tế vi mô', 3, 'Nguyên lý cơ bản của kinh tế vi mô', 2),
                                                                          ('Kinh tế vĩ mô', 3, 'Nguyên lý cơ bản của kinh tế vĩ mô', 2),
                                                                          ('Nguyên lý kế toán', 3, 'Các nguyên tắc và phương pháp kế toán', 2),
                                                                          ('Quản trị học', 3, 'Khái niệm và lý thuyết quản trị', 2),
                                                                          ('Tài chính doanh nghiệp', 3, 'Quản lý tài chính trong doanh nghiệp', 2),
                                                                          ('Marketing căn bản', 3, 'Các khái niệm cơ bản về marketing', 2),
                                                                          ('Thống kê kinh doanh', 3, 'Phương pháp thống kê ứng dụng', 2);

-- Khoa Cơ khí (faculty_id = 3)
INSERT INTO subjects (subject_name, credits, description, faculty_id) VALUES
                                                                          ('Vật liệu kỹ thuật', 3, 'Tính chất và ứng dụng vật liệu', 3),
                                                                          ('Cơ học kỹ thuật', 4, 'Tĩnh học và động học vật rắn', 3),
                                                                          ('Sức bền vật liệu', 3, 'Tính toán độ bền của chi tiết máy', 3),
                                                                          ('Nguyên lý máy', 3, 'Cấu tạo và nguyên lý hoạt động của máy', 3);

-- Khoa Luật (faculty_id = 4)
INSERT INTO subjects (subject_name, credits, description, faculty_id) VALUES
                                                                          ('Lý luận nhà nước và pháp luật', 3, 'Nguồn gốc và bản chất của nhà nước, pháp luật', 4),
                                                                          ('Luật Hiến pháp', 3, 'Quy định về tổ chức nhà nước', 4),
                                                                          ('Luật Dân sự', 4, 'Quy định về quan hệ tài sản và nhân thân', 4);

-- Khoa Ngoại ngữ (faculty_id = 5)
INSERT INTO subjects (subject_name, credits, description, faculty_id) VALUES
                                                                          ('Ngữ âm tiếng Anh', 2, 'Luyện phát âm chuẩn', 5),
                                                                          ('Ngữ pháp tiếng Anh', 3, 'Cấu trúc ngữ pháp cơ bản và nâng cao', 5),
                                                                          ('Đọc hiểu tiếng Anh', 3, 'Kỹ năng đọc hiểu văn bản học thuật', 5);

-- =============================================
-- Bảng Giảng viên (teachers) - FK -> faculties
-- =============================================
-- Khoa CNTT (faculty_id = 1)
INSERT INTO teachers (name, academic_rank, phone_number, email, experience, faculty_id) VALUES
                                                                                            ('Nguyễn Văn Bình', 'PGS. TS.', '0912111222', 'binh.nv@university.edu.vn', 15, 1),
                                                                                            ('Lê Thị Hoa', 'TS.', '0912333444', 'hoa.lt@university.edu.vn', 10, 1),
                                                                                            ('Phạm Tuấn Anh', 'ThS.', '0912555666', 'anh.pt@university.edu.vn', 5, 1),
                                                                                            ('Hoàng Văn Long', 'TS.', '0912777888', 'long.hv@university.edu.vn', 12, 1);

-- Khoa Kinh tế (faculty_id = 2)
INSERT INTO teachers (name, academic_rank, phone_number, email, experience, faculty_id) VALUES
                                                                                            ('Trần Thị Dung', 'PGS. TS.', '0987111222', 'dung.tt@university.edu.vn', 18, 2),
                                                                                            ('Nguyễn Mạnh Hùng', 'TS.', '0987333444', 'hung.nm@university.edu.vn', 9, 2),
                                                                                            ('Vũ Thị Thu', 'ThS.', '0987555666', 'thu.vt@university.edu.vn', 6, 2),
                                                                                            ('Đỗ Minh Quang', 'TS.', '0987777888', 'quang.dm@university.edu.vn', 11, 2);

-- Khoa Cơ khí (faculty_id = 3)
INSERT INTO teachers (name, academic_rank, phone_number, email, experience, faculty_id) VALUES
                                                                                            ('Lý Văn Sơn', 'PGS. TS.', '0911111222', 'son.lv@university.edu.vn', 20, 3),
                                                                                            ('Hoàng Đức Trung', 'TS.', '0911333444', 'trung.hd@university.edu.vn', 13, 3),
                                                                                            ('Mai Thị Ngọc', 'ThS.', '0911555666', 'ngoc.mt@university.edu.vn', 7, 3),
                                                                                            ('Bùi Văn Nam', 'TS.', '0911777888', 'nam.bv@university.edu.vn', 10, 3);

-- Khoa Luật (faculty_id = 4)
INSERT INTO teachers (name, academic_rank, phone_number, email, experience, faculty_id) VALUES
                                                                                            ('Đặng Thị Thảo', 'PGS. TS.', '0955111222', 'thao.dt@university.edu.vn', 16, 4),
                                                                                            ('Lê Minh Đức', 'TS.', '0955333444', 'duc.lm@university.edu.vn', 8, 4),
                                                                                            ('Phan Thị Kim Liên', 'ThS.', '0955555666', 'lien.ptk@university.edu.vn', 4, 4);

-- Khoa Ngoại ngữ (faculty_id = 5)
INSERT INTO teachers (name, academic_rank, phone_number, email, experience, faculty_id) VALUES
                                                                                            ('Trịnh Quang Vinh', 'PGS. TS.', '0933111222', 'vinh.tq@university.edu.vn', 19, 5),
                                                                                            ('Ngô Thị Hà', 'TS.', '0933333444', 'ha.nt@university.edu.vn', 11, 5),
                                                                                            ('Chu Văn An', 'ThS.', '0933555666', 'an.cv@university.edu.vn', 5, 5);

-- =============================================
-- Bảng Lớp học (classes) - FK -> subjects, teachers
-- =============================================
-- Giả sử ID giảng viên từ 1 đến 18, ID môn học từ 1 đến 26
INSERT INTO classes (subject_id, teacher_id, semester, academic_year, room, study_date, start_time, end_time) VALUES
-- Học kỳ 1, 2024-2025
(1, 1, 'Học kỳ 1', '2024-2025', 'A1-101', '2024-09-09', '07:00:00', '09:00:00'), -- Nhập môn LT - GV1
(2, 2, 'Học kỳ 1', '2024-2025', 'A1-102', '2024-09-09', '09:30:00', '12:00:00'), -- CTDL - GV2
(3, 3, 'Học kỳ 1', '2024-2025', 'A1-103', '2024-09-10', '07:00:00', '09:00:00'), -- CSDL - GV3
(9, 5, 'Học kỳ 1', '2024-2025', 'B2-201', '2024-09-09', '13:00:00', '15:00:00'), -- Kinh tế vi mô - GV5
(10, 6, 'Học kỳ 1', '2024-2025', 'B2-202', '2024-09-10', '13:00:00', '15:00:00'), -- Kinh tế vĩ mô - GV6
(17, 9, 'Học kỳ 1', '2024-2025', 'C3-301', '2024-09-11', '07:00:00', '09:00:00'), -- Vật liệu KT - GV9
(21, 13, 'Học kỳ 1', '2024-2025', 'D4-401', '2024-09-12', '09:30:00', '12:00:00'), -- Lý luận NN - GV13
(24, 16, 'Học kỳ 1', '2024-2025', 'E5-501', '2024-09-13', '13:00:00', '15:00:00'), -- Ngữ âm TA - GV16

-- Học kỳ 2, 2024-2025
(4, 4, 'Học kỳ 2', '2024-2025', 'A1-104', '2025-01-20', '07:00:00', '09:00:00'), -- Mạng MT - GV4
(5, 1, 'Học kỳ 2', '2024-2025', 'A1-101', '2025-01-20', '09:30:00', '12:00:00'), -- HĐH - GV1
(11, 7, 'Học kỳ 2', '2024-2025', 'B2-203', '2025-01-21', '07:00:00', '09:00:00'), -- Nguyên lý KT - GV7
(12, 8, 'Học kỳ 2', '2024-2025', 'B2-201', '2025-01-21', '13:00:00', '15:00:00'), -- Quản trị học - GV8
(18, 10, 'Học kỳ 2', '2024-2025', 'C3-302', '2025-01-22', '09:30:00', '12:00:00'), -- Cơ học KT - GV10
(22, 14, 'Học kỳ 2', '2024-2025', 'D4-402', '2025-01-23', '07:00:00', '09:00:00'), -- Luật HP - GV14
(25, 17, 'Học kỳ 2', '2024-2025', 'E5-502', '2025-01-24', '13:00:00', '15:00:00'), -- Ngữ pháp TA - GV17

-- Thêm vài lớp nữa cho đủ số lượng
(6, 3, 'Học kỳ 1', '2024-2025', 'A1-105', '2024-09-11', '09:30:00', '12:00:00'), -- Phát triển Web - GV3
(7, 4, 'Học kỳ 1', '2024-2025', 'A1-106', '2024-09-12', '13:00:00', '15:00:00'), -- Trí tuệ N T - GV4
(13, 5, 'Học kỳ 1', '2024-2025', 'B2-204', '2024-09-13', '07:00:00', '09:00:00'), -- Tài chính DN - GV5
(19, 11, 'Học kỳ 1', '2024-2025', 'C3-303', '2024-09-09', '15:30:00', '17:30:00'), -- Sức bền VL - GV11
(23, 15, 'Học kỳ 1', '2024-2025', 'D4-403', '2024-09-10', '15:30:00', '17:30:00'), -- Luật Dân sự - GV15
(8, 2, 'Học kỳ 2', '2024-2025', 'A1-102', '2025-01-22', '13:00:00', '15:00:00'), -- An toàn TT - GV2
(14, 6, 'Học kỳ 2', '2024-2025', 'B2-205', '2025-01-23', '09:30:00', '12:00:00'), -- Marketing CB - GV6
(20, 12, 'Học kỳ 2', '2024-2025', 'C3-304', '2025-01-24', '07:00:00', '09:00:00'), -- Nguyên lý máy - GV12
(26, 18, 'Học kỳ 2', '2024-2025', 'E5-503', '2025-01-20', '15:30:00', '17:30:00'); -- Đọc hiểu TA - GV18

-- =============================================
-- Bảng Sinh viên (students) - FK -> faculties, classes (nullable)
-- =============================================
-- Giả sử ID Khoa từ 1-5, ID Lớp từ 1-24
INSERT INTO students (student_code, name, date_of_birth, faculty_id, phone, email, class_id) VALUES
                                                                                                 ('SV001', 'Nguyễn Văn An', '2004-01-15', 1, '0910000001', 'an.nv@student.edu.vn', 1), -- CNTT, Lớp 1
                                                                                                 ('SV002', 'Trần Thị Bình', '2004-02-20', 1, '0910000002', 'binh.tt@student.edu.vn', 1), -- CNTT, Lớp 1
                                                                                                 ('SV003', 'Lê Văn Cường', '2004-03-10', 1, '0910000003', 'cuong.lv@student.edu.vn', 2), -- CNTT, Lớp 2
                                                                                                 ('SV004', 'Phạm Thị Dung', '2004-04-05', 1, '0910000004', 'dung.pt@student.edu.vn', 2), -- CNTT, Lớp 2
                                                                                                 ('SV005', 'Hoàng Văn Em', '2003-05-25', 1, '0910000005', 'em.hv@student.edu.vn', 9),  -- CNTT, Lớp 9 (HK2)

                                                                                                 ('SV006', 'Vũ Thị Giáng', '2004-06-12', 2, '0980000006', 'giang.vt@student.edu.vn', 4), -- Kinh tế, Lớp 4
                                                                                                 ('SV007', 'Đỗ Văn Hùng', '2004-07-18', 2, '0980000007', 'hung.dv@student.edu.vn', 4), -- Kinh tế, Lớp 4
                                                                                                 ('SV008', 'Nguyễn Thị Lan', '2004-08-01', 2, '0980000008', 'lan.nt@student.edu.vn', 5), -- Kinh tế, Lớp 5
                                                                                                 ('SV009', 'Lý Văn Minh', '2003-09-09', 2, '0980000009', 'minh.lv@student.edu.vn', 5), -- Kinh tế, Lớp 5
                                                                                                 ('SV010', 'Trần Thị Ngọc', '2004-10-30', 2, '0980000010', 'ngoc.tt@student.edu.vn', 11), -- Kinh tế, Lớp 11 (HK2)

                                                                                                 ('SV011', 'Phạm Văn Quang', '2004-11-11', 3, '0911000011', 'quang.pv@student.edu.vn', 6), -- Cơ khí, Lớp 6
                                                                                                 ('SV012', 'Hoàng Thị Sen', '2004-12-24', 3, '0911000012', 'sen.ht@student.edu.vn', 6), -- Cơ khí, Lớp 6
                                                                                                 ('SV013', 'Bùi Văn Tuấn', '2003-01-07', 3, '0911000013', 'tuan.bv@student.edu.vn', 13), -- Cơ khí, Lớp 13 (HK2)

                                                                                                 ('SV014', 'Vũ Thị Uyên', '2004-02-14', 4, '0955000014', 'uyen.vt@student.edu.vn', 7), -- Luật, Lớp 7
                                                                                                 ('SV015', 'Đặng Văn Vinh', '2004-03-22', 4, '0955000015', 'vinh.dv@student.edu.vn', 7), -- Luật, Lớp 7
                                                                                                 ('SV016', 'Lê Thị Xuân', '2003-04-19', 4, '0955000016', 'xuan.lt@student.edu.vn', 14), -- Luật, Lớp 14 (HK2)

                                                                                                 ('SV017', 'Ngô Văn Yến', '2004-05-08', 5, '0933000017', 'yen.nv@student.edu.vn', 8), -- Ngoại ngữ, Lớp 8
                                                                                                 ('SV018', 'Chu Thị Ánh', '2004-06-27', 5, '0933000018', 'anh.ct@student.edu.vn', 8), -- Ngoại ngữ, Lớp 8
                                                                                                 ('SV019', 'Trần Văn Bảo', '2003-07-03', 5, '0933000019', 'bao.tv@student.edu.vn', 15), -- Ngoại ngữ, Lớp 15 (HK2)

                                                                                                 ('SV020', 'Nguyễn Kim Chi', '2004-08-16', 1, '0910000020', 'chi.nk@student.edu.vn', 3), -- CNTT, Lớp 3
                                                                                                 ('SV021', 'Lê Đức Duy', '2004-09-21', 2, '0980000021', 'duy.ld@student.edu.vn', 12), -- Kinh tế, Lớp 12 (HK2)
                                                                                                 ('SV022', 'Phạm Minh Hiếu', '2003-10-02', 3, '0911000022', 'hieu.pm@student.edu.vn', 18), -- Cơ khí, Lớp 18 (HK2)
                                                                                                 ('SV023', 'Hoàng Thuỳ Linh', '2004-11-29', 4, '0955000023', 'linh.ht@student.edu.vn', 19), -- Luật, Lớp 19 (HK1)
                                                                                                 ('SV024', 'Bùi Quang Minh', '2004-12-13', 5, '0933000024', 'minh.bq@student.edu.vn', 24), -- Ngoại ngữ, Lớp 24 (HK2)
                                                                                                 ('SV025', 'Vũ Ngọc Nhi', '2003-01-26', 1, '0910000025', 'nhi.vn@student.edu.vn', 10), -- CNTT, Lớp 10 (HK2)
                                                                                                 ('SV026', 'Đỗ Thành Phát', '2004-02-09', 2, '0980000026', 'phat.dt@student.edu.vn', 17), -- Kinh tế, Lớp 17 (HK1)
                                                                                                 ('SV027', 'Nguyễn Thị Quỳnh', '2004-03-31', 3, '0911000027', 'quynh.nt@student.edu.vn', 20), -- Cơ khí, Lớp 20 (HK2)
                                                                                                 ('SV028', 'Lý Tấn Sang', '2003-04-11', 4, '0955000028', 'sang.lt@student.edu.vn', 23), -- Luật, Lớp 23 (HK1)
                                                                                                 ('SV029', 'Trần Uyển Vy', '2004-05-18', 5, '0933000029', 'vy.tu@student.edu.vn', NULL), -- Ngoại ngữ, Chưa có lớp
                                                                                                 ('SV030', 'Phạm Xuân Anh', '2004-06-07', 1, '0910000030', 'anh.px@student.edu.vn', NULL); -- CNTT, Chưa có lớp

-- =============================================
-- Bảng Điểm số (grades) - FK -> students (student_code), classes
-- =============================================
-- Dùng mã sinh viên SV001 đến SV028, ID lớp 1 đến 24
-- Lưu ý: Cột student_code trong grades tham chiếu đến student_code trong students
INSERT INTO grades (attendance_score, midterm_score, final_score, student_code, class_id) VALUES
-- SV001
(9.0, 8.5, 7.5, 'SV001', 1), -- Lớp 1
(8.0, 7.0, 8.0, 'SV001', 3), -- Lớp 3
(9.5, 9.0, 9.0, 'SV001', 9), -- Lớp 9 (HK2)
-- SV002
(8.5, 8.0, 8.5, 'SV002', 1), -- Lớp 1
(7.5, 7.5, 7.0, 'SV002', 3), -- Lớp 3
(8.0, 8.5, 8.0, 'SV002', 10), -- Lớp 10 (HK2)
-- SV003
(9.0, 9.0, 9.5, 'SV003', 2), -- Lớp 2
(8.5, 8.0, 7.5, 'SV003', 5), -- Lớp 5 (HK2)
-- SV004
(7.0, 6.5, 7.0, 'SV004', 2), -- Lớp 2
(6.0, 5.0, 6.5, 'SV004', 5), -- Lớp 5 (HK2)
-- SV006
(9.5, 8.5, 9.0, 'SV006', 4), -- Lớp 4
(8.0, 7.5, 8.0, 'SV006', 11), -- Lớp 11 (HK2)
-- SV007
(8.0, 8.0, 8.0, 'SV007', 4), -- Lớp 4
(7.0, 7.0, 7.5, 'SV007', 11), -- Lớp 11 (HK2)
-- SV011
(8.5, 7.5, 8.0, 'SV011', 6), -- Lớp 6
(7.5, 8.0, 7.0, 'SV011', 13), -- Lớp 13 (HK2)
-- SV014
(9.0, 9.5, 9.0, 'SV014', 7), -- Lớp 7
(8.5, 8.0, 8.5, 'SV014', 14), -- Lớp 14 (HK2)
-- SV017
(8.0, 7.0, 7.5, 'SV017', 8), -- Lớp 8
(7.5, 6.5, 7.0, 'SV017', 15), -- Lớp 15 (HK2)
-- Thêm điểm ngẫu nhiên
(7.0, 8.0, 6.5, 'SV005', 10), -- SV005 lớp 10
(8.0, 8.5, 7.5, 'SV008', 11), -- SV008 lớp 11
(9.0, 9.0, 8.5, 'SV009', 11), -- SV009 lớp 11
(7.5, 7.0, 8.0, 'SV010', 12), -- SV010 lớp 12
(6.5, 7.5, 7.0, 'SV012', 13), -- SV012 lớp 13
(8.0, 8.0, 9.0, 'SV013', 18), -- SV013 lớp 18
(9.5, 9.0, 9.5, 'SV015', 14), -- SV015 lớp 14
(8.5, 7.5, 8.0, 'SV016', 19), -- SV016 lớp 19
(7.0, 7.5, 6.0, 'SV018', 15), -- SV018 lớp 15
(8.0, 9.0, 8.5, 'SV019', 24), -- SV019 lớp 24
(9.0, 8.5, 7.5, 'SV020', 1), -- SV020 lớp 1
(8.0, 7.0, 8.0, 'SV020', 9), -- SV020 lớp 9
(9.5, 9.0, 9.0, 'SV021', 4), -- SV021 lớp 4
(8.5, 8.0, 8.5, 'SV021', 12), -- SV021 lớp 12
(7.5, 7.5, 7.0, 'SV022', 6), -- SV022 lớp 6
(8.0, 8.5, 8.0, 'SV022', 18), -- SV022 lớp 18
(9.0, 9.0, 9.5, 'SV023', 7), -- SV023 lớp 7
(8.5, 8.0, 7.5, 'SV023', 19), -- SV023 lớp 19
(7.0, 6.5, 7.0, 'SV024', 8), -- SV024 lớp 8
(6.0, 5.0, 6.5, 'SV024', 24), -- SV024 lớp 24
(9.5, 8.5, 9.0, 'SV025', 1), -- SV025 lớp 1
(8.0, 7.5, 8.0, 'SV025', 10), -- SV025 lớp 10
(8.0, 8.0, 8.0, 'SV026', 4), -- SV026 lớp 4
(7.0, 7.0, 7.5, 'SV026', 17), -- SV026 lớp 17
(8.5, 7.5, 8.0, 'SV027', 6), -- SV027 lớp 6
(7.5, 8.0, 7.0, 'SV027', 20), -- SV027 lớp 20
(9.0, 9.5, 9.0, 'SV028', 7), -- SV028 lớp 7
(8.5, 8.0, 8.5, 'SV028', 23); -- SV028 lớp 23

-- =============================================
-- Bảng Học phí (tuitions) - FK -> students
-- =============================================
-- Giả sử ID Sinh viên từ 1 đến 30, Mã sinh viên SV001 đến SV030
-- Enum PaymentStatus: 'PAID', 'UNPAID'
INSERT INTO tuitions (student_code, semester, amount, start_date, end_date, status, student_id) VALUES
-- Học kỳ 1, 2024-2025 (15 SV đã đóng)
('SV001', 'Học kỳ 1', 15000000.00, '2024-08-01', '2024-09-30', 'PAID', 1),
('SV002', 'Học kỳ 1', 15000000.00, '2024-08-01', '2024-09-30', 'PAID', 2),
('SV003', 'Học kỳ 1', 15000000.00, '2024-08-01', '2024-09-30', 'PAID', 3),
('SV004', 'Học kỳ 1', 15000000.00, '2024-08-01', '2024-09-30', 'PAID', 4),
('SV006', 'Học kỳ 1', 14000000.00, '2024-08-01', '2024-09-30', 'PAID', 6),
('SV007', 'Học kỳ 1', 14000000.00, '2024-08-01', '2024-09-30', 'PAID', 7),
('SV008', 'Học kỳ 1', 14000000.00, '2024-08-01', '2024-09-30', 'PAID', 8),
('SV009', 'Học kỳ 1', 14000000.00, '2024-08-01', '2024-09-30', 'PAID', 9),
('SV011', 'Học kỳ 1', 16000000.00, '2024-08-01', '2024-09-30', 'PAID', 11),
('SV012', 'Học kỳ 1', 16000000.00, '2024-08-01', '2024-09-30', 'PAID', 12),
('SV014', 'Học kỳ 1', 13000000.00, '2024-08-01', '2024-09-30', 'PAID', 14),
('SV015', 'Học kỳ 1', 13000000.00, '2024-08-01', '2024-09-30', 'PAID', 15),
('SV017', 'Học kỳ 1', 12000000.00, '2024-08-01', '2024-09-30', 'PAID', 17),
('SV018', 'Học kỳ 1', 12000000.00, '2024-08-01', '2024-09-30', 'PAID', 18),
('SV020', 'Học kỳ 1', 15000000.00, '2024-08-01', '2024-09-30', 'PAID', 20),

-- Học kỳ 1, 2024-2025 (15 SV chưa đóng)
('SV005', 'Học kỳ 1', 15000000.00, '2024-08-01', '2024-09-30', 'UNPAID', 5),
('SV010', 'Học kỳ 1', 14000000.00, '2024-08-01', '2024-09-30', 'UNPAID', 10),
('SV013', 'Học kỳ 1', 16000000.00, '2024-08-01', '2024-09-30', 'UNPAID', 13),
('SV016', 'Học kỳ 1', 13000000.00, '2024-08-01', '2024-09-30', 'UNPAID', 16),
('SV019', 'Học kỳ 1', 12000000.00, '2024-08-01', '2024-09-30', 'UNPAID', 19),
('SV021', 'Học kỳ 1', 14000000.00, '2024-08-01', '2024-09-30', 'UNPAID', 21),
('SV022', 'Học kỳ 1', 16000000.00, '2024-08-01', '2024-09-30', 'UNPAID', 22),
('SV023', 'Học kỳ 1', 13000000.00, '2024-08-01', '2024-09-30', 'UNPAID', 23),
('SV024', 'Học kỳ 1', 12000000.00, '2024-08-01', '2024-09-30', 'UNPAID', 24),
('SV025', 'Học kỳ 1', 15000000.00, '2024-08-01', '2024-09-30', 'UNPAID', 25),
('SV026', 'Học kỳ 1', 14000000.00, '2024-08-01', '2024-09-30', 'UNPAID', 26),
('SV027', 'Học kỳ 1', 16000000.00, '2024-08-01', '2024-09-30', 'UNPAID', 27),
('SV028', 'Học kỳ 1', 13000000.00, '2024-08-01', '2024-09-30', 'UNPAID', 28),
('SV029', 'Học kỳ 1', 12000000.00, '2024-08-01', '2024-09-30', 'UNPAID', 29),
('SV030', 'Học kỳ 1', 15000000.00, '2024-08-01', '2024-09-30', 'UNPAID', 30),

-- Học kỳ 2, 2024-2025 (Chưa ai đóng)
('SV001', 'Học kỳ 2', 15500000.00, '2025-01-15', '2025-02-28', 'UNPAID', 1),
('SV002', 'Học kỳ 2', 15500000.00, '2025-01-15', '2025-02-28', 'UNPAID', 2),
('SV003', 'Học kỳ 2', 15500000.00, '2025-01-15', '2025-02-28', 'UNPAID', 3),
('SV004', 'Học kỳ 2', 15500000.00, '2025-01-15', '2025-02-28', 'UNPAID', 4),
('SV006', 'Học kỳ 2', 14500000.00, '2025-01-15', '2025-02-28', 'UNPAID', 6);