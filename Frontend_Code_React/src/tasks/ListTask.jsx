import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';
import { faTrash } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {faPenToSquare} from '@fortawesome/free-solid-svg-icons'






export default function HomePage() {

const [tasks, setTasks] = useState([]);

const {id} = useParams()

    useEffect(()=>{
      console.log("Coding with Rina");
      loadTasks();
    }, [])

    const loadTasks=async()=>{

          const results =await axios.get("http://localhost:8080/api/task/allTasks")
          //  console.log(results.data);
            setTasks(results.data)

    };

    //Delete Function

    const deleteTask =async(id)=>{

      await axios.delete(`http://localhost:8080/api/task/edit/${id}`)
      //  console.log(results.data);
        // setTasks(results.data)
        loadTasks()

};
   
             return (

               <div  className='container mt-5'>
                <h1>List of Things!</h1>
        <div className='card'>

          
    {/* <table className="table bg-light shadow table-secondary"> */}
    <table className="table table-striped table-dark">

    <thead className="thead-dark">
    <tr>
      <th scope="col">New Task</th>
      {/* <th scope="col">In Progress</th>
      <th scope="col">Completed</th> */}

    </tr> 
  </thead>
  <tbody>
<div>
    {
      tasks.map((task, index)=>(
<tr>
  {/* <th className="row" key={index}>{index+1}</th> */}
  <th className="row" key={index}></th>

      <td>{task.index}</td>
      <td>{task.description}</td>
      
      {/* <td>{task.completed}</td>
      <td>{task.Action}</td> */}
<td>        

                 <FontAwesomeIcon icon={faPenToSquare}/>

              <Link className="btn btn-outline-primary mx-2" to={`/edit/${task.id}`}>Edit</Link>

              <FontAwesomeIcon icon={faTrash} onClick={()=>deleteTask(task.id)}/>

              <button className="btn btn-danger mx-2" onClick={()=>deleteTask(task.id)}>Delete</button>

              <div>
         </div>
      </td>
    
   </tr>
    
    )
     ) }

    </div>
    
  </tbody>
</table>
</div>
  </div>
)
   }

//    export default HomePage
