/* INIT DATABASE */
USE [master]
GO
IF EXISTS (SELECT * FROM sys.databases WHERE name = 'OnlineJudgeDB')
	DROP DATABASE OnlineJudgeDB
GO
CREATE DATABASE OnlineJudgeDB
GO
USE OnlineJudgeDB
GO 
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON 
GO
CREATE TABLE [dbo].[users](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[createdAt] [datetime] NOT NULL,
	[updatedAt] [datetime] NOT NULL,
	[username] [nvarchar](255) NOT NULL,
	[password] [nvarchar](1000) NULL,
	[email] [nvarchar](255) NOT NULL,
	[score] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
CREATE TABLE [dbo].[tasks](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[createdAt] [datetime] NOT NULL,
	[updatedAt] [datetime] NOT NULL,
	[taskname] [nvarchar](255) NULL,
	[taskDescription] [nvarchar](255) NOT NULL,
	[timeLimit] [nvarchar](255) NOT NULL,
	[memoryLimit] [nvarchar](255) NULL,
	[slug] [nvarchar](255) NOT NULL,
	[score] [int] NULL,
)
GO
CREATE TABLE [dbo].[submissions](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[createdAt] [datetime] NOT NULL,
	[updatedAt] [datetime] NOT NULL,
	[username] [nvarchar](255) NOT NULL,
	[taskname] [nvarchar](255) NULL,
	[status] [nvarchar](255) NOT NULL,
	[slug] [nvarchar](255) NOT NULL,
	[code] [nvarchar](1000) NULL,
)
GO
CREATE TABLE [dbo].[testcases](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[createdAt] [datetime] NOT NULL,
	[updatedAt] [datetime] NOT NULL,
	[slug] [nvarchar](255) NOT NULL,
	[input] [nvarchar](255) NOT NULL,
	[output] [nvarchar](255) NOT NULL,
)
GO
SET IDENTITY_INSERT [dbo].[users] ON
GO
INSERT [dbo].[users] ([id], [createdAt], [updatedAt], [username], [password], [email], [score]) VALUES (1, CAST(N'2023-06-12T22:43:59.877' AS DateTime), CAST(N'2023-06-12T22:43:59.877' AS DateTime), N'admin', N'1', N'admin@gmail.com', 0)
GO
SET IDENTITY_INSERT [dbo].[users] OFF
