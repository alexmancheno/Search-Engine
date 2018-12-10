import React,  {Component} from 'react';
import axios from 'axios';
import style from './style.css';

export default class IndexedPages extends Component {
    constructor(props) {
        super(props);
        axios.defaults.headers.get['Access-Control-Allow-Origin'] = '*';
        this.getIndexedPages = this.getIndexedPages.bind(this);
        this.state = {
            indexedPages: []
        }
        this.getIndexedPages();
    }

    getIndexedPages() {
        let api = process.env.API_URL + '/getIndexedPages';
        axios.get(api)
        .then(res => {
            this.setState({indexedPages: res.data});
        }).catch(error => {
            console.log(error);
        })
    }

    render() {
        return (
            <div className="container">
            <h5>Indexed pages</h5>
                <ul className={"list-group " + style.scrollableListGroup } >
                {
                    this.state.indexedPages.map(row => (
                        <li key={row.toString()}className="list-group-item">{row}</li>
                    )) 
                }
                </ul>
            </div>
        )
    }
}