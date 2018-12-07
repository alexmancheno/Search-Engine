import React,  {Component} from 'react';
import axios from 'axios';
import style from './style.css';

export default class SearchBar extends Component {
    constructor(props) {
        super(props);
        this.runPhatSearch = this.runPhatSearch.bind(this);
    }

    runPhatSearch(query) {
        console.log("hey!");
        axios.get('https://localhost:8080/search', {
            params: {
                query: "hello"
            }
        }).then(res => {
            console.log(res);
        }).catch(error => {
            console.log(error);
        })
         
    }

    render() {
        return (
            <div className={style.main} >
                <div className="input-group">
                    <input type="text" className="form-control" placeholder="Get a phat search" />
                    <div className="input-group-append">
                        <button className="btn btn-secondary" type="button" onClick={e => this.runPhatSearch('testing')}>
                            <i className="fa fa-search"></i>
                        </button>
                    </div>
                </div>
            </div>
        )
    }
}