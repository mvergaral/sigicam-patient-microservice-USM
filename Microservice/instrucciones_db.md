1. **Para acceder a psql en WSL**
    ```
    sudo -u postgres psql
    ```

2. **Crear Base de datos**
    ```SQL
    create role calceuser with createdb login password 'calceteam.1234';
    ALTER ROLE calceuser SUPERUSER;
    create database calcedb;
    alter database calcedb owner to calceuser;
    grant all privileges on database calcedb to calceuser;
    ```