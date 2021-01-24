## An xv6 lottery scheduler
the xv6 by default use a round robin scheduling algorithm. so we will change it to a lottery scheduler. </br>
The lottery in a nutshell is a probabilistic scheduling algorithm, and the basic idea is to give the processes lottery tickets for various system resources, such as cup time. Whenever a scheduling decision has to be made a lottery ticket is chosen at random, and the process holding that ticket gets the resource. More important processes can be given extra tickets to increase their odds of winning. Lottery scheduling also solves the problem of starvation. Giving each process at least one lottery ticket guarantees that it has a non-zero probability of being selected at each scheduling operation.</br>
In conclusion what the scheduler will be doing is given two processes A and B, if A has 60 tickets from 1-60 and B has 40 tickets from 61-100. The scheduler will pick a random number from 1 to 100 if the picked number is from 1 to 60 then A is the winner else if the picked number is from 61 to 100 then b is the winner.</br>

### we will implement two system calls:
1 - `int settickets(int number)` </br>
Which sets the number of tickets to the calling process. As mentioned above every process has 1 ticket by default, but it may call this routine to raise the number of tickets it has and receive a higher proportion of the cpu.  
2 - `int getpinfo(struct pstat *)`</br>
This system call returns some info about all the running processes such as the process id, number of tickets, how many ticks this process accumulated up til now and whether this process is in use or not. We will make use of this system call in the testing phase of the lottery scheduler, But in general we can use it for any task related to processes information.
it's a variant of the command line program `ps` which is used to know what is going on.

### Steps:
1- Make a system call which allows you to set the tickets for a process.</br>
2- Code to generate a random number.</br>
3- In the scheduler function count the total number of tickets for all processes that are runnable.</br>
4- Generate a random number between 0 and the total tickets calculated above.</br>
5- When looping through the processes keep the counter of the total number of tickets passed.</br>
6- Just when the counter becomes greater the random value we got, run the process.</br>
7- Put a break at the end of for loop so that we don't execute the processes following the process we just run.</br>


#### Making the settickets system call:</br>
First we need to set the process structure up for the system call. So we need to make couple of changes in `proc.c` and `proc.h`. </br>
Go to `proc.h` and in the struct proc add two variables, one for the tickets and the other for the ticks. </br>
```
int tickets;
int ticks;
```
Then we want to initialize these variables to make the processes have initially one ticket. Any new process is allocated through the `allocproc` function, So go to `proc.c` and in the allocproc we see that it scan the process table to locate an empty slot which will be holding `UNUSED` label and if found it will jump to the found label to initialize some variables such as pid and so on, in this section we initialize our variables also
```
p->tickets = 1;
p->ticks = 0;
```
Like and ordinary system call we will change Five files: </br>
- syscall.h
- syscall.c
- sysproc.c
- usys.S
- user.h

In `syscall.h` There is a number assigned to every system call. And there is initially 21 of them already defined </br>
Add the following line with the appropriate number

>    #define SYS_settickets    XX

In `syscall.c` Add a pointer to the system call </br>
this file contains array of function pointer which use the number we assigned in syscall.h as a pointer to the system call which will be defined in differen file. so add this line in its appropriate position:</br>

>    [SYS_settickets]   sys_settickets

This means, when system call occurred with system call number XX, function pointed by function pointer sys_getreadcount will be called.</br>
Also in this file is add the function prototype so as to be able to define it in different place. So add this line </br>

>    extern int sys_settickets(void)

In `sysproc.c` we implement the system call function.</br>
```
int
sys_settickets(void)
{
  int num_tickets;
  if (argint(0, &num_tickets) < 0)
    return -1;
  
  settickets(num_tickets);
  return 0;
}
```
In `usys.S` we add interface to make the userprogram able to call the system call </br>

>    SYSCALL(settickets)

Finally in `user.h` we add the function which will be called from the user program

>    int settickets(void);

In the scheduler function count the total number of tickets for all processes that are runnable.</br
#### In `proc.c` at the scheduler function count the total number of tickets for all runnable processes
```
for(total_tickets = 0, p = ptable.proc; p < &ptable.proc[NPROC]; p++)
      if(p->state==RUNNABLE)
        total_tickets+=p->tickets;
```


#### Perform the randomized Lottery.</br>
using random at most function to generate number between 0 and total_tickets</br>

>	winner = random_at_most(total_tickets);

#### Determine which process owns this ticket
We can do this by using a counter starts from zero and accumelates the tickets every iteration until we hit the range of the winner process when counter value is greater than the winner value</br>
this section inside the function might look like this
```
...
for(p = ptable.proc; p < &ptable.proc[NPROC]; p++){
      if(p->state != RUNNABLE) {
            continue;
      }

      // looking for the winner range
      counter += p->tickets;

      if (counter < winner) {
            continue;
      }

      p->ticks += 1;
      
      ...
```
After that we should break from the loop to not execute the process next to the current process and make the sceduler start the whole process again.


## One last thing to do is handling the fork system call
we need to make the child process has the same tickets as the parent, so it's an easy job we can perform it by adding the following line to the fork function in the appropriate place:

>	 np->tickets = curproc->tickets;

this line will make the child inherit the parent tickets.

























## What is a system call ?

<p> System call provides the services of the operating system to the user programs via Application Program Interface(API). It provides an interface between a process and operating system to allow user-level processes to request services of the operating system.
so all programs need resources must use system calls
</p>

### Services provided by the system call:
- Process creation and management
- Main memory management
- File Access, Directory and File system management
- Device handling(I/O)
- Protection
- Networking, etc.


### System call types:
- Process control: end, abort, create, terminate, allocate and free memory.
			   Ex: fork(), Exit(), Wait()
- File management: create, open, close, delete, read file etc.
			   Ex open(), read(), write(), close()
- Device management
			   Ex: read(), write()
- Information maintenance
			   Ex: getpid(), sleep()
- Communication
			   Ex: pipe()
			
			
### How to add a system Call to xv6 ?

You need to make changes to 5 files:</br>
- syscall.h
- syscall.c
- sysproc.c
- usys.S
- user.h

<b>Example: Adding system call returns the read system call count</b>

Firstly we set up the process structure appropriatly in `proc.h` in the struct proc ADD a new variable `int readcount` to hold the number of system calls for each created process. </br>
Then in `proc.c` file exactly in allocproc function we have to set our variable initially with zero, The function first search for UNUSED process in the process table then jumb to the found label, here where we will initiallize our variable `p->readcount = 0;` </br>

Now we are ready to start implementing the systeme call</br>

In `syscall.h` There is a number assigned to every system call. And there is initially 21 of them already defined </br>

Add this line at the end of the file: </br>

>    #define SYS_getreadcount    22

In `syscall.c` Add a pointer to the system call </br>
this file contains array of function pointer which use the number we assigned in syscall.h as a pointer to the system call which will be defined in differen file. so add this line in its appropriate position:</br>

>    [SYS_getreadcount]   sys_getreadcount

This means, when system call occurred with system call number 22, function pointed by function pointer sys_getreadcount will be called.</br>

The syscall function in this file called whenever system call is made so we have to check whether if the system call was a read system call then increase our counter.</br>
```
if (num == SYS_read)
	curproc->readcount = curproc->readcount + 1;
```

Last thing with this file is adding the function prototype so as to be able to define it in different place. So add this line </br>

>    extern int sys_getreadcount(void)

In `sysproc.c` we implement the system call function, we can acess the current process in this file using myproc().</br>
```
int
sys_getreadcount(void)
{
	return myproc()->readcount;
}
```
The above function returns the current value of the readcount variable </br>

In `usys.S` we add interface to make the userprogram able to call the system call </br>

>    SYSCALL(getreadcount)

Finally in `user.h` we add the function which will be called from the user program

>    int getreadcount(void);

Now we are done with the system call implementation and we need to make the userprogram to use the system call </br>
Make a new file called `readcount.c` 
```
#include "types.h"
#include "stat.h"
#include "user.h"

int
main(int argc, char *argv[])
{
	char buffer[512];
	int ptr = open("test.txt", 'r');

	read(ptr, buffer, 512);
	read(ptr, buffer, 512);
	read(ptr, buffer, 512);

	
	printf(1, "The number of read syscall: %d\n", getreadcount());


    exit();
}
```
this is some useless read calls to make sure that our new system call is working properly, Note that you have to create a test.txt for testing purpose.</br>
In order to add the user program open `Makefile` and in UPROGS add your program to the list `_readcount\` then add the file name with the files in the EXTRA section add `readcount.c` </br>
Now it's time for testing </br>

>    make qemu

>    readcount

you should now see `The number of read syscall: 3` And you are Done. :)











## Table of contents

- [Description](#Description)
- [Used Devices](#Used-Devices)
- [Circuit Diagram](#Circuit-Diagram)
- [Features](#Features)
- [Team members](#Team-members)

        
    

## Team members
- [Mohamed Fathy](https://github.com/Mohamed-Fathy-Salah)
- [Omar Mohamed](https://github.com/omarmohamed101)
- [Ahmed Nashaat](https://github.com/AhmadNashaat0)
- [Ahmed Yasser](https://github.com/ahmadyasser01)
- [Shahenda Hamdy](https://github.com/shahendahamdy)

