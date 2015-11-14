SELECT sender.lastname, sender.firstname, tasks.tasktype, recipient.firstname, recipient.lastname,tasks.isdone, tasks.isconfirmed
FROM tasks 
 INNER JOIN users AS sender
 INNER JOIN users AS recipient
 ON sender.id=tasks.id_sender AND recipient.id=tasks.id_recipient