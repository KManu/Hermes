
-- Table: CompanyInformation
CREATE TABLE CompanyInformation ( 
    Advert               TEXT,
    Category             TEXT NOT NULL,
    Description          TEXT,
    Email                TEXT DEFAULT 'None Available',
    Facebook             TEXT DEFAULT 'None Available',
    Google               TEXT DEFAULT 'None Available',
    GPS                  TEXT,
    IdentificationNumber TEXT NOT NULL
                              PRIMARY KEY,
    Location             TEXT NOT NULL,
    Logo                 TEXT,
    Name                 TEXT NOT NULL,
    PhoneNumber          TEXT NOT NULL,
    Postal               TEXT,
    Tags                 TEXT,
    Twitter              TEXT DEFAULT 'None Available',
    Website              TEXT DEFAULT 'None Available' 
);

INSERT INTO [CompanyInformation] ([Advert], [Category], [Description], [Email], [Facebook], [Google], [GPS], [IdentificationNumber], [Location], [Logo], [Name], [PhoneNumber], [Postal], [Tags], [Twitter], [Website]) VALUES (null, 'Entertainment Production
', 'A small Ghanaian gaming company which specialises in making high quality pilolo games for the international market
', 'GameOver@Gameover.net
', 'Not Available', 'Not Available', '5.627364, -0.211908
', 1, 'Achimota
', 'https://pbs.twimg.com/profile_images/503577251032879104/jlV9jBYM.jpeg', 'GameOver', '0245064087 0249223989', null, 'Gaming Entertainment Animation Video Games Technology', 'Not Available', 'GameOver.net');
INSERT INTO [CompanyInformation] ([Advert], [Category], [Description], [Email], [Facebook], [Google], [GPS], [IdentificationNumber], [Location], [Logo], [Name], [PhoneNumber], [Postal], [Tags], [Twitter], [Website]) VALUES (null, 'Assasinations ', 'The best company to go to for local contract killings, assasinations, etc. Most affordable rates on the market. Targets must have an evil rating of 9.0 or be kittehs to be assasination candidates. Open 32/5 from 6 to 9 pm', 'soyouwanttokillthem@nice.com', 'Not Available', 'Not Available', '5.627567, -0.211234', 2, 'Tema', 'https://pbs.twimg.com/profile_images/2299283575/h2sjhhlljhyrkl5zgzzn_400x400.jpeg', 'Candy Hill', '0325125745 992952165', null, 'Assasinations Contract Killings Death Kittehs', 'Not Available', 'KillThemAll.com');
