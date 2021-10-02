create or replace view exportCi as (
select b.id                                                                                                as id,
b.merchantId                                                                                        as merchantId,
en.Name                                                                                             as merchant,
b.branch                                                                                            as branchId,
br.name                                                                                             as branchName,
b.lastUpdatedOn                                                                                     as date,
cD.name                                                                                             as name,
b.uniId                                                                                             as uniTrId,
b.bankTransRef                                                                                      as bankTransRef,
b.merchantReference                                                                                 as merRef,
ud.fullName                                                                                         as teller,
b.makerId                                                                                           as tellerId,
pS.id                                                                                               as statusId,
pS.Name                                                                                             as status,
b.amount                                                                                            as amount,
(select GROUP_CONCAT(i2.name, ' X ', i2.qty, ': ', i2.total SEPARATOR ', ')
from itemDetail i2
where i2.id in (select i.itemDetail_id from itemPaymentDetail i where i.paymentDetail_id = pD.id)) as items

from customerRegisteredPayment b
join customerData cD on b.customerData_id = cD.id
join user u on u.id = b.makerId
join UsersData ud on ud.id = u.userData
join paymentStatus pS on b.status = pS.id
join itemPaymentDetail il on b.itemPaymentDetail_id = il.id
join paymentDetail pD on il.paymentDetail_id = pD.id
join branch br on b.branch = br.id
join enterprises en on en.uniEnterpriseId like b.merchantId
);

create or replace view exportMi as (
select b.id                                                                                                as id,
b.merchantId                                                                                        as merchantId,
en.Name                                                                                             as merchant,
b.branch                                                                                            as branchId,
br.name                                                                                             as branchName,
b.lastUpdatedOn                                                                                     as date,
cD.name                                                                                             as name,
b.uniId                                                                                             as uniTrId,
b.bankTransRef                                                                                      as bankTransRef,
b.merchantReference                                                                                 as merRef,
ud.fullName                                                                                         as teller,
b.makerId                                                                                           as tellerId,
pS.id                                                                                               as statusId,
pS.Name                                                                                             as status,
b.amount                                                                                            as amount,
(select GROUP_CONCAT(i2.name, ' X ', i2.qty, ': ', i2.total SEPARATOR ', ')
from itemDetail i2
where i2.id in (select i.itemDetail_id from itemPaymentDetail i where i.paymentDetail_id = pD.id)) as items

from bankIntraction b
join customerData cD on b.customerData_id = cD.id
join user u on u.id = b.makerId
join UsersData ud on ud.id = u.userData
join paymentStatus pS on b.status = pS.id
join itemPaymentDetail il on b.itemPaymentDetail_id = il.id
join paymentDetail pD on il.paymentDetail_id = pD.id
join branch br on b.branch = br.id
join enterprises en on en.uniEnterpriseId like b.merchantId
);
