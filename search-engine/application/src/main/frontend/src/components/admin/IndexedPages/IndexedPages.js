import React,  {Component} from 'react';
import axios from 'axios';

export default class IndexedPages extends Component {
    constructor(props) {
        super(props);
        axios.defaults.headers.get['Access-Control-Allow-Origin'] = '*';
        this.getIndexedPages = this.getIndexedPages.bind(this);
    }

    getIndexedPages() {

        let results;
        let api = process.env.API_URL + '/getIndexedPages';
        axios.get(api)
        .then(res => {
            let keys = res.data;
            console.log(keys);
            results = Object.keys(keys).map(item => {
                <li className='list-group-item'>{keys[item]}</li>
            })
            return results;
        }).catch(error => {
            console.log(error);
        })
    }

    render() {
        let listItems = this.getIndexedPages();

        return (
            <div className="container">
                <ul className="list-group">
                <li className='list-group-item'>test</li>
                    {listItems}
                </ul>
            </div>
        )
    }
}