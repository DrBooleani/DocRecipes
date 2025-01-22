CREATE TABLE "operations" (
  "id" integer PRIMARY KEY,
  "type_id" varchar,
  "room_id" integer,
  "patient_id" integer,
  "state" varchar,
  "start_date" timestamp
);

CREATE TABLE "operation_types" (
  "name" varchar PRIMARY KEY,
  "room_type" varchar,
  "duration_hours" integer
);

CREATE TABLE "operation_providers" (
  "id" integer PRIMARY KEY,
  "type" varchar,
  "name" varchar
);

CREATE TABLE "assets" (
  "id" integer PRIMARY KEY,
  "type" varchar,
  "name" varchar
);

CREATE TABLE "optype_opproviders" (
  "optype_id" varchar,
  "opprovider_id" integer,
  PRIMARY KEY ("optype_id", "opprovider_id")
);

CREATE TABLE "optype_assets" (
  "optype_id" varchar,
  "asset_id" integer,
  PRIMARY KEY ("optype_id", "asset_id")
);

CREATE TABLE "inventory" (
  "asset_id" integer,
  "count" integer
);

CREATE TABLE "team_members" (
  "id" integer PRIMARY KEY,
  "name" varchar,
  "opprovider_id" integer
);

CREATE TABLE "operation_rooms" (
  "id" integer PRIMARY KEY,
  "room_nr" integer,
  "building_block" varchar,
  "floor" varchar,
  "type" varchar,
  "state" varchar
);

CREATE TABLE "room_inventory" (
  "room_id" integer,
  "asset_id" integer,
  "count" integer
);

CREATE TABLE "pre_operative_assesments" (
  "name" varchar PRIMARY KEY
);

CREATE TABLE "patients" (
  "id" integer PRIMARY KEY,
  "name" varchar,
  "nin" integer
);

CREATE TABLE "assesments" (
  "team_member_id" integer,
  "patient_id" integer,
  "pre_op_a_id" varchar,
  PRIMARY KEY ("team_member_id", "patient_id", "pre_op_a_id")
);

CREATE TABLE "optype_pre_op_a" (
  "optype_id" varchar,
  "pre_op_a_id" varchar,
  PRIMARY KEY ("optype_id", "pre_op_a_id")
);

CREATE TABLE "operation_reports" (
  "id" integer PRIMARY KEY,
  "team_member_id" integer,
  "operation_id" integer,
  "report" varchar,
  PRIMARY KEY ("team_member_id", "operation_id")
);

CREATE TABLE "operation_team_member" (
  "operation_id" integer,
  "team_member_id" integer,
  PRIMARY KEY ("operation_id", "team_member_id")
);

ALTER TABLE "operation_team_member" ADD FOREIGN KEY ("operation_id") REFERENCES "operations" ("id");

ALTER TABLE "operation_team_member" ADD FOREIGN KEY ("team_member_id") REFERENCES "team_members" ("id");

ALTER TABLE "operation_reports" ADD FOREIGN KEY ("operation_id") REFERENCES "operations" ("id");

ALTER TABLE "operation_reports" ADD FOREIGN KEY ("team_member_id") REFERENCES "team_members" ("id");

ALTER TABLE "operations" ADD FOREIGN KEY ("patient_id") REFERENCES "patients" ("id");

ALTER TABLE "optype_assets" ADD FOREIGN KEY ("asset_id") REFERENCES "assets" ("id");

ALTER TABLE "optype_assets" ADD FOREIGN KEY ("optype_id") REFERENCES "operation_types" ("name");

ALTER TABLE "operations" ADD FOREIGN KEY ("type_id") REFERENCES "operation_types" ("name");

ALTER TABLE "optype_opproviders" ADD FOREIGN KEY ("opprovider_id") REFERENCES "operation_providers" ("id");

ALTER TABLE "optype_opproviders" ADD FOREIGN KEY ("optype_id") REFERENCES "operation_types" ("name");

ALTER TABLE "inventory" ADD FOREIGN KEY ("asset_id") REFERENCES "assets" ("id");

ALTER TABLE "team_members" ADD FOREIGN KEY ("opprovider_id") REFERENCES "operation_providers" ("id");

ALTER TABLE "operations" ADD FOREIGN KEY ("room_id") REFERENCES "operation_rooms" ("id");

ALTER TABLE "room_inventory" ADD FOREIGN KEY ("room_id") REFERENCES "operation_rooms" ("id");

ALTER TABLE "room_inventory" ADD FOREIGN KEY ("asset_id") REFERENCES "assets" ("id");

ALTER TABLE "optype_pre_op_a" ADD FOREIGN KEY ("optype_id") REFERENCES "operation_types" ("name");

ALTER TABLE "optype_pre_op_a" ADD FOREIGN KEY ("pre_op_a_id") REFERENCES "pre_operative_assesments" ("name");

ALTER TABLE "assesments" ADD FOREIGN KEY ("patient_id") REFERENCES "patients" ("id");

ALTER TABLE "assesments" ADD FOREIGN KEY ("pre_op_a_id") REFERENCES "pre_operative_assesments" ("name");

ALTER TABLE "assesments" ADD FOREIGN KEY ("team_member_id") REFERENCES "team_members" ("id");
