CREATE TABLE IF NOT EXISTS  person (
  userId INT NOT NULL PRIMARY KEY,
  carNum VARCHAR NOT NULL,
  carAddedTime TIMESTAMP NOT NULL,
  approvedStatus BOOLEAN NOT NULL,
  carApprovedTime TIMESTAMP
);