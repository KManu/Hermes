-- Creator:       MySQL Workbench 6.1.6/ExportSQLite plugin 2009.12.02
-- Author:        Kobby
-- Caption:       New Model
-- Project:       Name of the project
-- Changed:       2014-06-19 14:06
-- Created:       2014-06-09 19:55
PRAGMA foreign_keys = OFF;

-- Schema: mydb
BEGIN;
CREATE TABLE "CompanyInformation"(
  "IdentificationNumber" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "Name" TEXT NOT NULL,
  "Phone Number" INTEGER NOT NULL,
  "Location" TEXT NOT NULL,
  "Logo" BLOB NOT NULL,
  "Website" TEXT DEFAULT 'No webstite Available',
  "Email" TEXT DEFAULT 'No email available',
  "Category" TEXT NOT NULL,
  "Postal" TEXT,
  "Description" TEXT,
  "GPS" TEXT,
  "Tags" TEXT,
  "Advert" BLOB,
  "Facebook" TEXT DEFAULT 'Not Available',
  "Twitter" TEXT DEFAULT 'Not Available',
  "Google" TEXT DEFAULT 'Not Available',
  CONSTRAINT "IdentificationNumber_UNIQUE"
    UNIQUE("IdentificationNumber"),
  CONSTRAINT "Name_UNIQUE"
    UNIQUE("Name"),
  CONSTRAINT "Phone Number_UNIQUE"
    UNIQUE("Phone Number"),
  CONSTRAINT "Email_UNIQUE"
    UNIQUE("Email"),
  CONSTRAINT "Twitter_UNIQUE"
    UNIQUE("Twitter"),
  CONSTRAINT "Facebook_UNIQUE"
    UNIQUE("Facebook"),
  CONSTRAINT "Google_UNIQUE"
    UNIQUE("Google")
);
COMMIT;
