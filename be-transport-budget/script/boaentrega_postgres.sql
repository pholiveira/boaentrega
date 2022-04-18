-- boaentrega.tb_wareshouse definition

-- Drop table

-- DROP TABLE boaentrega.tb_wareshouse;

CREATE TABLE boaentrega.tb_wareshouse (
	id numeric NOT NULL,
	latitude varchar NOT NULL,
	longitude varchar NOT NULL,
	postcode varchar NULL,
	active bool NOT NULL DEFAULT true,
	"type" varchar NULL,
	CONSTRAINT tb_storage_id_pk PRIMARY KEY (id)
);

-- boaentrega.tb_locality_postcode definition

-- Drop table

-- DROP TABLE boaentrega.tb_locality_postcode;

CREATE TABLE boaentrega.tb_locality_postcode (
	id numeric NOT NULL,
	state varchar NOT NULL,
	locality varchar NOT NULL,
	postcode_begin numeric NOT NULL,
	postcode_end numeric NOT NULL,
	local_warehouse numeric NULL,
	state_warehouse numeric NULL,
	regional_warehouse numeric NULL,
	CONSTRAINT tb_locality_postcode_pk PRIMARY KEY (id)
);

-- boaentrega.tb_locality_postcode foreign keys

ALTER TABLE boaentrega.tb_locality_postcode ADD CONSTRAINT tb_locality_postcode_fk FOREIGN KEY (local_warehouse) REFERENCES boaentrega.tb_wareshouse(id);
ALTER TABLE boaentrega.tb_locality_postcode ADD CONSTRAINT tb_locality_postcode_fk_1 FOREIGN KEY (state_warehouse) REFERENCES boaentrega.tb_wareshouse(id);
ALTER TABLE boaentrega.tb_locality_postcode ADD CONSTRAINT tb_locality_postcode_fk_2 FOREIGN KEY (regional_warehouse) REFERENCES boaentrega.tb_wareshouse(id);

-- boaentrega.tb_warehouse_schedules definition

-- Drop table

-- DROP TABLE boaentrega.tb_warehouse_schedules;

CREATE TABLE boaentrega.tb_warehouse_schedules (
	id numeric NOT NULL,
	warehouse numeric NOT NULL,
	schedules varchar NOT NULL,
	"type" varchar NOT NULL
);


-- boaentrega.tb_warehouse_schedules foreign keys

ALTER TABLE boaentrega.tb_warehouse_schedules ADD CONSTRAINT tb_warehouse_schedules_fk FOREIGN KEY (warehouse) REFERENCES boaentrega.tb_wareshouse(id);
