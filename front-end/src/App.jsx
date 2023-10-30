import { useState } from 'react';
import './App.css';
import axios from 'axios';
import { useEffect } from 'react';
import DisplayAbdouReclamation from './components/DisplayAbdouReclamation';
import DisplayAbdouReclamationWithResponses from './components/DisplayAbdouReclamationWithResponses';
import DisplayAllReclamationsAndResponsesWithClientAndAdminData from './components/DisplayAllReclamationsAndResponsesWithClientAndAdminData';
import ShowSearchResult from './components/ShowSearchResult';
import Displayproduct from './components/product/Displayproduct'
const base_url = 'http://localhost:8005/api'
function App() {
    const [allReclamation, setAllReclamation] = useState([]);

    const [displayAllReclamation, setDisplayAllReclamation] = useState(false)

    const [allProduct, setAllProduct] = useState([]);
    const [displayAllProduct, setDisplayAllProduct] = useState(false)


    const [allReclamationsAndResponses,setAllReclamationAndResponses] = useState([])
    const [displayReclamationAndResponses, setDisplayReclamationAndResponses] = useState(false)

    const [allReclamationsAndResponsesWithClientAndAdminData,setAllReclamationsAndResponsesWithClientAndAdminData] = useState([])
    const [displayAllReclamationsAndResponsesWithClientAndAdminData, setDisplayAllReclamationsAndResponsesWithClientAndAdminData] = useState(false)


    const [searchReclamationInput, setSearchReclamationInput] = useState('')
    const [searchReclamationResult,setSearchReclamationResult] = useState([])
    const [showResult,setShowResult] = useState(false)


    async function fetchAllReclamations() {
        const { data } = await axios.get(`${base_url}/abdou/reclamations`)
        setAllReclamation(data)
    }
    async function fetchAllProduct() {
        const { data } = await axios.get(`${base_url}/product`)
        setAllProduct(data)
    }
    async function fetchReclamationsAndResponses() {
        const { data } = await axios.get(`${base_url}/abdou/getReclamationsAndResponses`)
        setAllReclamationAndResponses(data)
    }
    async function fetchClientsReclamationsWithClienDataAndAdminData() {
        const { data } = await axios.get(`${base_url}/abdou/getClientsReclamationsWithClienDataAndAdminData`)
        setAllReclamationsAndResponsesWithClientAndAdminData(data)
    }

    async function searchReclamation() {
        const { data } = await axios.get(`${base_url}/abdou/searchReclamation/${searchReclamationInput}`)
        setSearchReclamationResult(data)
        setShowResult(prev => !prev)

    }

    useEffect(() => {
        fetchAllProduct()
        fetchAllReclamations()
        fetchReclamationsAndResponses()
        fetchClientsReclamationsWithClienDataAndAdminData()
    },[])

    return (
        <div>
            <div>
                <button onClick={() => setDisplayAllProduct(prev => !prev)}>Display All Product </button>
                {allProduct && displayAllProduct && <Displayproduct allProduct={allProduct}/>}
            </div>
            <div>
                <button onClick={() => setDisplayAllReclamation(prev => !prev)}>Display All Reclamations </button>
                {allReclamation && displayAllReclamation && <DisplayAbdouReclamation allReclamation={allReclamation}/>}
            </div>

            <div>
                <button onClick={() => setDisplayReclamationAndResponses(prev => !prev)}>Display All Reclamations with responses </button>
                {allReclamationsAndResponses && displayReclamationAndResponses && <DisplayAbdouReclamationWithResponses allReclamationsAndResponses={allReclamationsAndResponses}/>}
            </div>

            <div>
                <button onClick={() => setDisplayAllReclamationsAndResponsesWithClientAndAdminData(prev => !prev)}>
                    Display All Reclamations with responses with client and admin data
                </button>
                {allReclamationsAndResponsesWithClientAndAdminData && displayAllReclamationsAndResponsesWithClientAndAdminData &&
                    <DisplayAllReclamationsAndResponsesWithClientAndAdminData allReclamationsAndResponsesWithClientAndAdminData={allReclamationsAndResponsesWithClientAndAdminData} />}
            </div>

            <div>
                <input type="text" placeholder='search for reclamation' value={searchReclamationInput} onChange={(e) => setSearchReclamationInput(e.target.value)} />
                <button onClick={searchReclamation}>Search</button>
                {showResult && searchReclamationResult && <ShowSearchResult searchReclamationResult={searchReclamationResult}/> }
            </div>
        </div>
    )
}

export default App