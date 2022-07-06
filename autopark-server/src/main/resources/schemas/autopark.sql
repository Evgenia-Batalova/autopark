CREATE TABLE IF NOT EXISTS auto_personnel (
    id serial PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    father_name VARCHAR(20)
);

CREATE TABLE auto (
    id serial PRIMARY KEY,
    num VARCHAR(20),
    color VARCHAR(20),
    mark VARCHAR(20),
    personnel_id INTEGER,
    FOREIGN KEY (personnel_id)
                  REFERENCES auto_personnel (id)
);

CREATE TABLE routes (
    id serial PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE journal (
    id serial PRIMARY KEY,
    time_out TIMESTAMP,
    time_in TIMESTAMP,
    auto_id INTEGER,
    route_id INTEGER,
    FOREIGN KEY (auto_id)
                     REFERENCES auto (id),
    FOREIGN KEY (route_id)
                     REFERENCES routes (id)
);



