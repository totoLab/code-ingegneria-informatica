CREATE DATABASE myDB1;

use myDB1;

CREATE TABLE Merce (
    codice INT PRIMARY KEY,
    marca VARCHAR(50)
);

CREATE TABLE Fornitore (
    PIVA VARCHAR(20) PRIMARY KEY,
    citta VARCHAR(50)
);

CREATE TABLE Fornitura (
    merce INT,
    fornitore VARCHAR(20),
    PRIMARY KEY (merce, fornitore),
    FOREIGN KEY (merce) REFERENCES Merce(codice),
    FOREIGN KEY (fornitore) REFERENCES Fornitore(PIVA)
);

INSERT IGNORE INTO Merce (codice, marca) VALUES
(1, 'Brand A'),
(2, 'Brand B'),
(3, 'Brand C'),
(4, 'Brand D');

INSERT IGNORE INTO Fornitore (PIVA, citta) VALUES
('F1', 'CS'),
('F2', 'NY'),
('F3', 'CS');

INSERT INTO Fornitura (merce, fornitore) VALUES
(1, 'F1'),
(2, 'F1'),
(3, 'F2'),
(4, 'F3'),
(1, 'F2');

select T.merce, T.fornitore
from Fornitura T;