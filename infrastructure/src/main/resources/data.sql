insert into account (id, label, iban, currency, balance) values (1, 'account1', 'FR7630001007941234567890185', 'EUR', 1000)
insert into account (id, label, iban, currency, balance) values (2, 'account2', 'FR7630001007941234567890186', 'EUR', 500)

insert into operation (id, label, amount, date, type_operation, account_id) values (1, 'op1', 500.00, '2021-10-23', 'DEPOSIT', 1)
insert into operation (id, label, amount, date, type_operation, account_id) values (2, 'op2', 500.00, '2021-10-24', 'DEPOSIT', 1)

insert into account_operations(account_id, operations_id) values (1,1)
insert into account_operations(account_id, operations_id) values (1,2)
