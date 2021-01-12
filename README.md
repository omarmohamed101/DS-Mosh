
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

<b>Example Adding system call returns count the read system call</b>

In `syscall.h` There is a number assigned to every system call. And there is initially 21 of them already defined 







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

