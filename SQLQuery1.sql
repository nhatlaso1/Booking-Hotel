CREATE database BookingHotel
go

use BookingHotel
go


CREATE table tblRole(
	RoleId bit primary key,
	RoleName varchar(50)
)


CREATE table tblAccount(
	userId varchar(50) primary key,
	Password varchar(100),
	RoleId bit foreign key references tblRole(RoleId),
	Status nvarchar(20)

)

CREATE table tblUser(
	userId varchar(50) foreign key references tblAccount(userId),
	Name nvarchar(50),
	Address Nvarchar(200),
	PhoneNumber varchar(12)
)


CREATE table tblHotel(
	hotelId int identity(0,1) primary key,
	hotelName nvarchar(50),
	hotelAddress nvarchar(200),
	Status bit
)

alter table tblHotel
add hotelQuantity int


CREATE table tblTypeRoom(
	typeId varchar(10) primary key,
	typeName varchar(50)
)


CREATE table tblRoom(
	hotelId int foreign key references tblHotel(hotelId),
	roomNo int identity (0,1) primary key,
	roomName varchar(50),
	availableDate date,
	quantity int,
	typeId varchar(10) foreign key references tblTypeRoom(typeId),
	roomPrice float
)


CREATE table tblFeedBack(
	roomNo int foreign key references tblRoom(roomNo),
	userId varchar(50) foreign key references tblAccount(userId),
	value int,
)

CREATE table tblOrder(
	orderId varchar(50) primary key,
	userId varchar(50) foreign key references tblAccount(userId),
	orderDate date,
	total float,
	status bit
)


CREATE table tblOrderDetails(
	orderId varchar(50) foreign key references tblOrder(orderId),
	roomNo int foreign key references tblRoom(roomNo),
	hotelId int foreign key references tblHotel(hotelId),
	orderQuantity int,
	night int,
	checkIn date,
	checkOut date
)


CREATE table tblDiscount(
	discountCode varchar(50) primary key,
	discountValue int,
	existDate date
) 


CREATE table tblAvailableHotel(
	hotelId int foreign key references tblHotel(hotelId),
	quanity int,
	availablequantity int,
)




Select *
from tblHotel

select *
from tblRoom
where tblRoom.hotelId = 2



select *
from tblRoom
where tblRoom.roomNo in (
	select quantity
	from tblRoom
	where tblRoom.roomNo = 5
	except
	select *
	from tblOrderDetails
			where roomNo = 10 and tblOrderDetails.checkOut between '2021-11-1' and '2021-11-05'

						
	)

	select *
	from tblRoom
	where roomNo = 5

	------------------------------------------------------------------------------
	--SELECT TOTAL QUANTITY ROOM
	select 
			(select quantity from tblRoom where tblRoom.roomNo = 9)
		- 
		(select SUM(orderQuantity) as Quantity from tblOrderDetails
			where roomNo = 9 and tblOrderDetails.checkOut between '2021-11-1' and '2021-11-05'
		)
		as subtractQuantity
		


	select 
		(select quantity from tblRoom where tblRoom.roomNo = 9)
		- 
		(select SUM(orderQuantity) as Quantity from tblOrderDetails
			where roomNo = 9 
		)
	as subtractQuantity
		------------------------------------------------------------------------------


	--SELECT TOTAL QUANTITY HOTEL

	select hotelId, hotelName, hotelAddress, hotelQuantity, Status
	from tblHotel
	where hotelName like ? and hotelAddress like ?






	select SUM(quantity) as quantity
	from tblRoom
	where tblRoom.hotelId = 3

	


	select *
	from tblRoom
	inner join tblOrderDetails on tblOrderDetails.roomNo = tblRoom.roomNo
	where tblRoom.roomNo = 5

	select *
	from tblHotel
	where hotelAddress like '%HCM%'

	select *
	from tblRoom
	where hotelId = 3


	
select *
from tblAvailableHotel

select *
from tblHotel
where hotelId = 3











select *
from tblOrderDetails
where hotelId = 0 and tblOrderDetails.checkOut between '2021-10-26' and '2021-10-30'



order by checkIn




