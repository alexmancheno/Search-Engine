import React,  {Component} from 'react';
import axios from 'axios';
import style from './style.css';

export default class SearchBar extends Component {
    constructor(props) {
        super(props);
        this.runPhatSearch = this.runPhatSearch.bind(this);
        axios.defaults.headers.get['Access-Control-Allow-Origin'] = '*';

        this.state = {
            searchQuery: ''
        }
    }

    handleSearchQueryChange(event) {
        this.setState({searchQuery: event.target.value});
    }

    runPhatSearch() {
        let api = process.env.API_URL + '/search';
        axios.get(api, {
            params: {
                query: this.state.searchQuery
            }
        }).then(res => {
            this.props.onSearch(res.data);
        }).catch(error => {
            console.log(error);
        })
        
        
    }

    render() {
        return (
            <div className={style.main} >
                <div className="input-group">
                    <input type="text" className="form-control" placeholder="Get a phat search" 
                        value={this.state.searchQuery} onChange={e => this.handleSearchQueryChange(e)}
                    />
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