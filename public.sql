/*
 Navicat Premium Data Transfer

 Source Server         : postgres
 Source Server Type    : PostgreSQL
 Source Server Version : 140007
 Source Host           : 117.50.174.154:5432
 Source Catalog        : postgres
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 140007
 File Encoding         : 65001

 Date: 18/03/2023 06:42:00
*/


-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS "public"."customer";
CREATE TABLE "public"."customer" (
  "id" int8 NOT NULL,
  "name" varchar(30) COLLATE "pg_catalog"."default",
  "email" varchar(20) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO "public"."customer" VALUES (1, 'sb', 'ddd', '1433223');
INSERT INTO "public"."customer" VALUES (2085752831, '', '', '');
INSERT INTO "public"."customer" VALUES (1138976292, 'weadasdsda', 'saasd', '123456.');

-- ----------------------------
-- Table structure for event
-- ----------------------------
DROP TABLE IF EXISTS "public"."event";
CREATE TABLE "public"."event" (
  "id" int8 NOT NULL,
  "name" varchar(30) COLLATE "pg_catalog"."default",
  "startdatetime" timestamp(6),
  "enddatetime" timestamp(6)
)
;

-- ----------------------------
-- Records of event
-- ----------------------------

-- ----------------------------
-- Table structure for ticket
-- ----------------------------
DROP TABLE IF EXISTS "public"."ticket";
CREATE TABLE "public"."ticket" (
  "id" int8 NOT NULL,
  "event_id" int8,
  "customer_id" int8
)
;

-- ----------------------------
-- Records of ticket
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table customer
-- ----------------------------
ALTER TABLE "public"."customer" ADD CONSTRAINT "customer_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table event
-- ----------------------------
ALTER TABLE "public"."event" ADD CONSTRAINT "event_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table ticket
-- ----------------------------
ALTER TABLE "public"."ticket" ADD CONSTRAINT "ticket_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table ticket
-- ----------------------------
ALTER TABLE "public"."ticket" ADD CONSTRAINT "ticket_customer_id_fkey" FOREIGN KEY ("customer_id") REFERENCES "public"."customer" ("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."ticket" ADD CONSTRAINT "ticket_event_id_fkey" FOREIGN KEY ("event_id") REFERENCES "public"."event" ("id") ON DELETE CASCADE ON UPDATE CASCADE;
