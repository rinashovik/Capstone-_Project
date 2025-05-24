

import React, {useEffect, useState} from 'react'
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';


  export default function EditTask () {

   let navigate = useNavigate()

   const {id} = useParams();

        const [task, setTask] = useState("")
       

        const onInputChange=(e)=>{
          setTask({...task, [e.target.name]:e.target.value});
          };
          
useEffect (() => {loadTasks()}, [])
        

        const handleSubmit = async(e) => {
            e.preventDefault();


 await axios.put(`http://localhost:8080/api/task/${id}`,task) 

 navigate("/editTask")
          setTask("")
        
      
        };

        const loadTasks=async()=>{

            const results =await axios.get(`http://localhost:8080/api/task/${id}`)
            //  console.log(results.data);
              setTask(results.data)
  
      };

          return (
            <div  className='container mt-5'>
              {/* <div className='card'> */}
                
        <form onSubmit={(e) => handleSubmit(e)}>

        <input   type={"text"} className="form-control"  name="description"  placeholder='try typing' onChange={(e) => onInputChange(e)} />
        <button className="btn btn-primary" type="submit">Submit</button>
        
        </form>
        </div>


          )
        }
