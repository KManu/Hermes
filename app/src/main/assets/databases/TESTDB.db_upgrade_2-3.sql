ALTER TABLE "CompanyInformation" RENAME TO "CompanyInformation_TMP";
CREATE TABLE "CompanyInformation" (
	"IdentificationNumber"	TEXT NOT NULL,
	"Name"	TEXT NOT NULL,
	"PhoneNumber"	TEXT NOT NULL,
	"Location"	TEXT NOT NULL,
	"Logo"	TEXT NOT NULL,
	"Website"	TEXT DEFAULT 'None Available',
	"Email"	TEXT DEFAULT 'None Available',
	"Category"	TEXT NOT NULL,
	"Postal"	TEXT,
	"Description"	TEXT DEFAULT 'None Available',
	"GPS"	TEXT,
	"Tags"	TEXT,
	"Advert"	TEXT,
	"Facebook"	TEXT DEFAULT 'None Available',
	"Twitter"	TEXT DEFAULT 'None Available',
	"Google"	TEXT DEFAULT 'None Available',
	PRIMARY KEY(IdentificationNumber)
);
DROP TABLE "CompanyInformation_TMP";