create DATABASE datnsd73
GO
use datnsd73
GO
CREATE TABLE [Product] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [avatar] varchar(225),
  [name] nvarchar(225),
  [descriptions] nvarchar(250),
  [date_create] date,
  [date_update] date,
  [id_nsx] int,
  [id_loai_giay] int,
  [id_chat_lieu] int,
  [id_de_giay] int,
  [status] int
)
GO

CREATE TABLE [nsx] (
  [id] integer PRIMARY KEY IDENTITY(1, 1),
  [name] nvarchar(225),
  [status] int
)
GO

CREATE TABLE [loai_giay] (
  [id] integer PRIMARY KEY IDENTITY(1, 1),
  [name] nvarchar(225)
)
GO

CREATE TABLE [de_giay] (
  [id] integer PRIMARY KEY IDENTITY(1, 1),
  [name] nvarchar(225)
)
GO

CREATE TABLE [Mau_sac] (
  [id] integer PRIMARY KEY IDENTITY(1, 1),
  [name] nvarchar(225)
)
GO

CREATE TABLE [Kich_co] (
  [id] integer PRIMARY KEY IDENTITY(1, 1),
  [name] nvarchar(225)
)
GO

CREATE TABLE [Product_detail] (
  [id] integer PRIMARY KEY IDENTITY(1, 1),
  [cost] int,
  [quantity] int,
  [sl__da_ban] int,
  [img_list] varchar(max),
  [id_mau_sac] int,
  [id_kich_co] int,
  [id_product] int,
  [date_create] date,
  [date_update] date
)
GO

CREATE TABLE [Chat_lieu] (
  [id] integer PRIMARY KEY IDENTITY(1, 1),
  [name] nvarchar(225)
)
GO

CREATE TABLE [Role] (
  [id] integer PRIMARY KEY IDENTITY(1, 1),
  [name] nvarchar(225)
)
GO

CREATE TABLE [Users] (
  [id] integer PRIMARY KEY IDENTITY(1, 1),
  [name] nvarchar(50),
  [gmail] varchar(100),
  [phone] varchar(15),
  [avatar] NVARCHAR(255),
  [password] varchar(100),
  [date_of_birth] date,
  [create_at] date,
  [update_at] date,
  [dia_chi] nvarchar(max),
  [id_role] int,
  [gender] bit,
  [google] varchar(100),
  [facebook] varchar(100),
  [is_activate] bit
)
GO

CREATE TABLE [Oder] (
  [id] integer PRIMARY KEY IDENTITY(1, 1),
  [name_user] nvarchar(50),
  [sdt_user] varchar(15),
  [id_user] int,
  [id_voucher] int,
  [total_money] float,
  [Note] nvarchar(200),
  [create_at] date,
  [status] int,
  [payment_method] nvarchar(100)
)
GO

CREATE TABLE [oder_detail] (
  [id] integer PRIMARY KEY IDENTITY(1, 1),
  [oder_id] int,
  [product_detail_id] int,
  [sl_product] int,
  [price] float
)
GO

CREATE TABLE [voucher] (
  [id] integer PRIMARY KEY IDENTITY(1, 1),
  [ma] VARCHAR(255) not null,
  [giam_gia] float NOT NULL,
  [don_toi_Thieu] decimal(18,2),
  [giam_toi_da] decimal(18,2),
  [sl_ap_dung] TIMESTAMP,
  [create_at] TIMESTAMP,
  [update_at] date,
  [date_begin] date,
  [date_end] date,
  [status] int
)
GO

CREATE TABLE [token] (
  [id] integer PRIMARY KEY IDENTITY(1, 1),
  [tokens] varchar(225) NOT NULL,
  [tokens_type] varchar(50) NOT NULL,
  [time_end] datetime NOT NULL,
  [revoked] int NOT NULL,
  [expired] int NOT NULL,
  [user_id] int
)I
GO

CREATE TABLE [social_account] (
  [id] integer PRIMARY KEY IDENTITY(1, 1),
  [provider] varchar(50) NOT NULL,
  [provider_id] varchar(50) NOT NULL,
  [email] VARCHAR(255) NOT NULL,
  [name] varchar(50) NOT NULL,
  [user_id] int
)
GO

CREATE TABLE [comment] (
  [id] int,
  [id_user] int,
  [id_product] int,
  [descriptions] nvarchar(200)
)
GO

ALTER TABLE [Product] ADD FOREIGN KEY ([id_nsx]) REFERENCES [nsx] ([id])
GO

ALTER TABLE [Product] ADD FOREIGN KEY ([id_loai_giay]) REFERENCES [loai_giay] ([id])
GO

ALTER TABLE [Product] ADD FOREIGN KEY ([id_de_giay]) REFERENCES [de_giay] ([id])
GO

ALTER TABLE [Product_detail] ADD FOREIGN KEY ([id_mau_sac]) REFERENCES [Mau_sac] ([id])
GO

ALTER TABLE [Product_detail] ADD FOREIGN KEY ([id_kich_co]) REFERENCES [Kich_co] ([id])
GO

ALTER TABLE [Product_detail] ADD FOREIGN KEY ([id_product]) REFERENCES [Product] ([id])
GO

ALTER TABLE [Product] ADD FOREIGN KEY ([id_chat_lieu]) REFERENCES [Chat_lieu] ([id])
GO

ALTER TABLE [Users] ADD FOREIGN KEY ([id_role]) REFERENCES [Role] ([id])
GO

ALTER TABLE [Oder] ADD FOREIGN KEY ([id_user]) REFERENCES [Users] ([id])
GO

ALTER TABLE [Oder] ADD FOREIGN KEY ([id_voucher]) REFERENCES [voucher] ([id])
GO

ALTER TABLE [oder_detail] ADD FOREIGN KEY ([oder_id]) REFERENCES [Oder] ([id])
GO

ALTER TABLE [oder_detail] ADD FOREIGN KEY ([product_detail_id]) REFERENCES [Product_detail] ([id])
GO

ALTER TABLE [token] ADD FOREIGN KEY ([user_id]) REFERENCES [Users] ([id])
GO

ALTER TABLE [social_account] ADD FOREIGN KEY ([user_id]) REFERENCES [Users] ([id])
GO

ALTER TABLE [comment] ADD FOREIGN KEY ([id_user]) REFERENCES [Users] ([id])
GO

ALTER TABLE [comment] ADD FOREIGN KEY ([id_product]) REFERENCES [Product] ([id])
GO

-- Thêm cột [ma] [don_toi_Thieu] decimal(18,2),[giam_toi_da] decimal(18,2) vào voucher 
-- Sửa trang thái status từ int qua varchar
-- Sua begindate, enddate tu date qua TIMESTAMP
-- ALTER TABLE [voucher] ADD [ma] VARCHARVA(255),
-- [don_toi_Thieu] decimal(18,2),
-- [giam_toi_da] decimal(18,2)

-- Alter table [voucher] alter column [date_begin] TIMESTAMP
-- Alter table [voucher] alter column [date_end] TIMESTAMP
-- Alter table [voucher] alter column [status] check ([status] in( 'ACTIVE', 'EXPIRED', 'INACTIVE', 'UPCOMING'))

-- SELECT * FROM voucher