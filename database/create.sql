-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2023-10-20 13:39:54.176

-- tables
-- Table: booking
CREATE TABLE booking (
    id serial  NOT NULL,
    schedule_id int  NOT NULL,
    first_name varchar(255)  NOT NULL,
    last_name varchar(255)  NOT NULL,
    CONSTRAINT booking_pk PRIMARY KEY (id)
);

-- Table: city
CREATE TABLE city (
    id serial  NOT NULL,
    name varchar(255)  NOT NULL,
    CONSTRAINT city_pk PRIMARY KEY (id)
);

-- Table: company
CREATE TABLE company (
    id serial  NOT NULL,
    name varchar(255)  NOT NULL,
    CONSTRAINT company_pk PRIMARY KEY (id)
);

-- Table: route
CREATE TABLE route (
    id serial  NOT NULL,
    from_city_id int  NOT NULL,
    to_city_id int  NOT NULL,
    distance int  NOT NULL,
    CONSTRAINT route_pk PRIMARY KEY (id)
);

-- Table: route_schedules
CREATE TABLE route_schedules (
    id serial  NOT NULL,
    route_id int  NOT NULL,
    schedule_id int  NOT NULL,
    CONSTRAINT route_schedules_pk PRIMARY KEY (id)
);

-- Table: schedule
CREATE TABLE schedule (
    id serial  NOT NULL,
    start_time_id int  NOT NULL,
    end_time_id int  NOT NULL,
    company_id int  NOT NULL,
    price int  NOT NULL,
    CONSTRAINT schedule_pk PRIMARY KEY (id)
);

-- Table: time
CREATE TABLE time (
    id serial  NOT NULL,
    date_time timestamp  NOT NULL,
    timezone_type int  NOT NULL,
    timezone varchar(255)  NOT NULL,
    CONSTRAINT time_pk PRIMARY KEY (id)
);

-- Table: timetable
CREATE TABLE timetable (
    id serial  NOT NULL,
    expire_date timestamp  NOT NULL,
    timezone_type int  NOT NULL,
    timezone varchar(255)  NOT NULL,
    CONSTRAINT timetable_pk PRIMARY KEY (id)
);

-- Table: timetable_routes
CREATE TABLE timetable_routes (
    id serial  NOT NULL,
    timetable_id int  NOT NULL,
    route_id int  NOT NULL,
    CONSTRAINT timetable_routes_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: booking_schedule (table: booking)
ALTER TABLE booking ADD CONSTRAINT booking_schedule
    FOREIGN KEY (schedule_id)
    REFERENCES schedule (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: end_time (table: schedule)
ALTER TABLE schedule ADD CONSTRAINT end_time
    FOREIGN KEY (end_time_id)
    REFERENCES time (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: from_city (table: route)
ALTER TABLE route ADD CONSTRAINT from_city
    FOREIGN KEY (from_city_id)
    REFERENCES city (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: route_city (table: route)
ALTER TABLE route ADD CONSTRAINT route_city
    FOREIGN KEY (to_city_id)
    REFERENCES city (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: route_schedules_route (table: route_schedules)
ALTER TABLE route_schedules ADD CONSTRAINT route_schedules_route
    FOREIGN KEY (route_id)
    REFERENCES route (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: route_schedules_schedule (table: route_schedules)
ALTER TABLE route_schedules ADD CONSTRAINT route_schedules_schedule
    FOREIGN KEY (schedule_id)
    REFERENCES schedule (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: schedule_company (table: schedule)
ALTER TABLE schedule ADD CONSTRAINT schedule_company
    FOREIGN KEY (company_id)
    REFERENCES company (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: start_time (table: schedule)
ALTER TABLE schedule ADD CONSTRAINT start_time
    FOREIGN KEY (start_time_id)
    REFERENCES time (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: timetable_routes_route (table: timetable_routes)
ALTER TABLE timetable_routes ADD CONSTRAINT timetable_routes_route
    FOREIGN KEY (route_id)
    REFERENCES route (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: timetable_routes_timetable (table: timetable_routes)
ALTER TABLE timetable_routes ADD CONSTRAINT timetable_routes_timetable
    FOREIGN KEY (timetable_id)
    REFERENCES timetable (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.

