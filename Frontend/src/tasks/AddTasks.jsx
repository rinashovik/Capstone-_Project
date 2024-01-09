

import React, {useState} from 'react'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';


  export default function AddTask () {

   let navigate = useNavigate()

        const [task, setTask] = useState("")
        
        const onInputChange=(e)=>{
          setTask({...task, [e.target.name]:e.target.value});


          };
           

        const handleSubmit = async(e) => {
            e.preventDefault();

          //  console.log(task);

 await axios.post("http://localhost:8080/api/task",task) 

 navigate("/addTask")


        //  AddTodo(task);
          setTask("")
        
        
        };



          return (
            <div  className='container mt-5'>
              {/* <div className='card'> */}
                
        <form onSubmit={(e) => handleSubmit(e)}>

        <input   type={"text"} className="form-control"  name="description"  placeholder='try typing' onChange={(e) => onInputChange(e)} />
        <button className="btn btn-primary" type="submit">Add Task</button>
        
        </form>
        </div>


          )
        }
